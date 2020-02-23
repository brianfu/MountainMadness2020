package com.sfucsss.mountainmadness2020.bathroom

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

//TODO: still have to find a way to pass an event from this instance's EndActivity to this instance, to kill this instance
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        begforPermission(this)

    }

    fun mapButtonClick(view : View){

        begforPermission(this)

        val mapIntent = Intent(this, MapsActivity::class.java)
        //Bundle is inited in MapsActivity
        startActivity(mapIntent)

        //Let this be the only activity that maintains itself constantly throughout the app's lifecycle

    }

    fun begforPermission(thisActivity : Activity){
        if (ContextCompat.checkSelfPermission(thisActivity, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) { //permissions are app-wide, may as well do it here, less messy
            // Permission is not granted

            //Request Permissions
            ActivityCompat.requestPermissions(thisActivity, arrayOf(Manifest.permission.READ_CONTACTS), 1)

            //as of now, this will crash if permissions not given

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        //pass
    }

}
