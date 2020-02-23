package com.sfucsss.mountainmadness2020.bathroom;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.yashovardhan99.timeit.Stopwatch;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, Stopwatch.OnTickListener {

    private GoogleMap mMap;
    public Stopwatch timer;
    //public CountTimer timer;

    //Latitude, Longitude
    MyMap myLocations;
    ArrayList<Pin> pins;
    //ArrayList<String> wordsFound = new ArrayList<String>();
    GameManager gameManager;
    Boolean mLocationPermissionGranted = false;
    private final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    //Stuff to pass
    //protected char lastLetter = 'a'; //default value
    //protected String currentWord = "";
    protected double timeTaken = 0.0; //set this up here and give it to a function (double is fine, in range 10E+308)
    protected int distanceTravelled = 0;
    //...

    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Location mLastKnownLocation; //call getDeviceLocation() to get
    private Location mDefaultLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        timer = new Stopwatch();
        timer.setOnTickListener(this);
        timer.start();

        getLocationPermission();
        mFusedLocationProviderClient = new FusedLocationProviderClient(this);
        gameManager = new GameManager(this);

        myLocations = new MyMap(this);
        pins = myLocations.locations();
    }

    //Start google maps garbage
    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (mLocationPermissionGranted) {
                Task locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.

                            mLastKnownLocation = (Location) task.getResult();
//                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
//                                    new LatLng(mLastKnownLocation.getLatitude(),
//                                            mLastKnownLocation.getLongitude()), 12));
                        } else {
                            Log.d("loc", "Current location is null. Using defaults.");
                            Log.e("loc", "Exception: %s", task.getException());
                            LatLng defLL = new LatLng(mDefaultLocation.getLatitude(), mDefaultLocation.getLongitude());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defLL, 12));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch(SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }
    //End google maps garbage


    private int counter = 200;

    @Override
    public void onTick(Stopwatch stopwatch){
        Log.d("Stopwatch (ms)", String.valueOf(stopwatch.getElapsedTime()));
        timeTaken = (double) stopwatch.getElapsedTime() / 1000; //Time is given in ms, convert to s

        if (counter == 0){
            View view = new View(this);
            getDeviceLocation();
            gameManager.update(mLastKnownLocation.getLongitude(), mLastKnownLocation.getLatitude(), timeTaken);
            counter = 200; //reset counter
        }else{
            counter--;
            Log.d("counter", "counter: " + counter);
        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        updateLocationUI();
        getDeviceLocation();

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//
//        Marker marker = mMap.addMarker(new MarkerOptions().position(sydney).title("A"));
//        marker.showInfoWindow();

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        ArrayList<Pin> pinlist = gameManager.allPins();
        LatLng newLoc = new LatLng(0.0, 0.0);
        for(Pin ele : pinlist){
            newLoc = new LatLng(ele.longitude, ele.latitude);
            Marker marker = mMap.addMarker(new MarkerOptions().position(newLoc).title(String.valueOf(ele.letter)));
            marker.showInfoWindow();
        }


        mMap.moveCamera(CameraUpdateFactory.newLatLng(newLoc));


    }

    private ArrayList<Pin> pinlistTest = new ArrayList<Pin>();
    public void onUpdate(View view){
        //update this every second if possible, if not just call it whenever the button's pressed
        //gameManager.update is currently broken (myMap NPE)


//        ArrayList<Pin> pinlist = gameManager.allPins();
//
//        Pin lastpin = new Pin(1500.0,1000.0, 'a'); //last thing pinned, move camera to this
//
//        ArrayList<Marker> markerlist = new ArrayList<Marker>();
//        for(Pin ele : pinlist){
//            LatLng newLoc = new LatLng(ele.latitude, ele.longitude);
//            Marker marker = mMap.addMarker(new MarkerOptions().position(newLoc).title(String.valueOf(ele.letter)));
//            //markerlist.add(marker);
//            marker.showInfoWindow();
//            lastpin = ele;
//        }


        getDeviceLocation();

        //Pin lastpin = pinlist.get(pinlist.size() - 1);
//        LatLng tmpLL = new LatLng(lastpin.latitude, lastpin.longitude);
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(tmpLL));

//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
//                new LatLng(mLastKnownLocation.getLatitude(),
//                        mLastKnownLocation.getLongitude()), 12));


        //updateLocationUI();
    }


    private int statsResultCode = 1111;
    public void onStatsClick(View view){
        Intent statIntent = new Intent(this, StatsActivity.class);
        Bundle extras = new Bundle();

        timer.pause();


        //Put stuff in bundle
        extras.putChar("lastLetter", gameManager.lastLetter());
        extras.putString("currentWord", gameManager.currentString()); //use this in resultActivity, wordsFound is not updated yet
        extras.putDouble("timeTaken", timeTaken); //todo
        extras.putInt("distanceTravelled", distanceTravelled); //todo
        extras.putStringArrayList("wordsFound", gameManager.allString());
        extras.putBoolean("addWordResult", gameManager.isValid()); //put in a valid word
        extras.putBoolean("isEnding", false); //reset the flag
        //TODO: put more stuff in


        statIntent.putExtras(extras);
        startActivityForResult(statIntent, statsResultCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent); //java likes super constructor calls on override

        // Check which request we're responding to
        if (requestCode == statsResultCode) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                    wordAdded(intent);
            }else{
                timer.resume();
            }
        }
    }

    public void wordAdded(Intent intent){ //this is the intent propagating back from resultActivity

        gameManager.finishedCurrentString(); //add the good word to the dict if so

        Bundle extras = intent.getExtras();


        boolean isEnding = extras.getBoolean("isEnding");
        if (isEnding){ //timer is paused as of here
            timer.stop();
        }else{
            timer.resume();
        }

        extras.putStringArrayList("wordsFound", gameManager.allString()); //update wordsFound
        this.getIntent().putExtras(extras); //also puts in useLastLetter (from resultActivity)

        //this.intent should now contain updated entries (in extra) on useLastLetter
        Boolean useLastLetter = extras.getBoolean("useLastLetter");
        //by default uses last letter rn
        //todo: update something with this info (gameManager should refresh && start at first letter again)

        //Don't kill it, keep persistent map
        //setResult(RESULT_OK);
        //finish();
    }
}
