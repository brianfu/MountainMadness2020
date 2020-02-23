package com.sfucsss.mountainmadness2020.bathroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun mapButtonClick(view : View){

        val mapIntent = Intent(this, MapsActivity::class.java)
        mapIntent.putExtra("hello", "world")

        startActivity(mapIntent)

    }

}
