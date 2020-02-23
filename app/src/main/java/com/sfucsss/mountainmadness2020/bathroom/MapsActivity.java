package com.sfucsss.mountainmadness2020.bathroom;

import androidx.core.util.Pair;
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


    private int RESULT_CODE = 1111;
    public void onStatsClick(View view){
        Intent statIntent = new Intent(this, StatsActivity.class);
        Bundle extras = new Bundle();


        //Put stuff in bundle
        extras.putChar("lastLetter", lastLetter);
        extras.putString("currentWord", currentWord);
        extras.putDouble("timeTaken", timeTaken);
        extras.putInt("distanceTravelled", distanceTravelled);
        extras.putStringArrayList("wordsFound", wordsFound);
        //TODO: put more stuff in


        statIntent.putExtras(extras);
        startActivityForResult(statIntent, RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent statIntent) {
        super.onActivityResult(requestCode, resultCode, statIntent); //java likes super constructor calls on override
        // Check which request we're responding to
        if (requestCode == RESULT_CODE) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                    kill();
            }
        }
    }

    public void kill(){
        setResult(RESULT_OK);
        finish();
    }
}
