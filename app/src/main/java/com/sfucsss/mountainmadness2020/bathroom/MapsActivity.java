package com.sfucsss.mountainmadness2020.bathroom;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    //Latitude, Longitude
    MyMap myLocations = new MyMap();
    ArrayList<Pin> pins = myLocations.locations();
    ArrayList<String> wordsFound = new ArrayList<String>();
    GameManager gameManager = new GameManager();

    //Stuff to pass
    protected char lastLetter = 'a'; //default value
    protected String currentWord = "";
    protected Double timeTaken = 0.0; //set this up here and give it to a function
    protected int distanceTravelled = 0;
    //...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //todo reverse countdowntimer init logic here
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


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);

        Marker marker = mMap.addMarker(new MarkerOptions().position(sydney).title("A"));
        marker.showInfoWindow();

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void onUpdate(View view){
        //update this every second if possible, if not just call it whenever the button's pressed
        //call gamemanager update

    }


    private int statsResultCode = 1111;
    public void onStatsClick(View view){
        Intent statIntent = new Intent(this, StatsActivity.class);
        Bundle extras = new Bundle();


        //Put stuff in bundle
        extras.putChar("lastLetter", gameManager.lastLetter());
        extras.putString("currentWord", gameManager.currentString()); //use this in resultActivity, wordsFound is not updated yet
        extras.putDouble("timeTaken", timeTaken); //todo
        extras.putInt("distanceTravelled", distanceTravelled); //todo
        extras.putStringArrayList("wordsFound", gameManager.allString());
        extras.putBoolean("addWordResult", gameManager.isValid()); //put in a valid word
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
            }
        }
    }

    public void wordAdded(Intent intent){ //this is the intent propagating back from resultActivity

        gameManager.finishedCurrentString(); //add the good word to the dict if so

        Bundle extras = intent.getExtras();
        extras.putStringArrayList("wordsFound", gameManager.allString()); //update wordsFound
        this.getIntent().putExtras(extras); //also puts in useLastLetter (from resultActivity)

        //this.intent should now contain updated entries (in extra) on useLastLetter
        //todo: update something with this info (gameManager should refresh && start at first letter again)

        //Don't kill it, keep persistent map (just finish the re
        //setResult(RESULT_OK);
        //finish();
    }
}
