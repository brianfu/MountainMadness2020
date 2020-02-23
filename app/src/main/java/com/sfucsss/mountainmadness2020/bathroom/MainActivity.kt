package com.sfucsss.mountainmadness2020.bathroom

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

//TODO: still have to find a way to pass an event from this instance's EndActivity to this instance, to kill this instance
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun mapButtonClick(view : View){

        val mapIntent = Intent(this, MapsActivity::class.java)
        //Bundle is inited in MapsActivity
        startActivity(mapIntent)

        //Let this be the only activity that maintains itself constantly throughout the app's lifecycle

    }

}
