package com.sfucsss.mountainmadness2020.bathroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class FinderActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    //Latitude, Longtitude
    var coords : ArrayList<Pair<Double, Double>> = arrayListOf(49.279692 to -122.925180,
        49.278593 to -122.924697, 49.278656 to -122.924826, 49.278726 to -122.922294, 49.279790 to -122.918861,
        49.275628 to -122.921601, 49.279855 to -122.918583, 49.281199 to -122.916630, 49.277211 to -122.916170,
        49.278114 to -122.914807, 49.277995 to -122.911867, 49.277888 to -122.909876, 49.280389 to -122.907480,
        49.277423 to -122.904471, 49.274531 to -122.912496);

    //Stuff to pass
    internal var lastLetter : Char = 'a'
    internal var current_word : String = ""
    //...



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finder)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("A"))
            .showInfoWindow()
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

    }

    fun onStatsClick(view : View){
        val statIntent = Intent(this, StatsActivity::class.java)
        val extras = Bundle()

        //Put stuff in bundle
        extras.putChar("lastLetter", lastLetter)

        statIntent.putExtras(extras)
        startActivity(statIntent)
    }

}
