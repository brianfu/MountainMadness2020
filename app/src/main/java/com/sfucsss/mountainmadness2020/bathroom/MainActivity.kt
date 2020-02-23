package com.sfucsss.mountainmadness2020.bathroom

import android.app.Activity
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
        //Bundle is inited in MapsActivity
        startActivity(mapIntent)

        setResult(Activity.RESULT_OK, mapIntent)
        finish(); //Kill this
    }

}
