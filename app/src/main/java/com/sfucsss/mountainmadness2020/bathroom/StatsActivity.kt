package com.sfucsss.mountainmadness2020.bathroom

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_stats.*

class StatsActivity : AppCompatActivity() {

    var addWordResult = false //True for good result, false for bad result
    lateinit var extras : Bundle
    val resultResultCode = 2222

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        extras = this.intent.extras ?: Bundle() //the intent doesn't exist before object construction!!!

        //Bundle should be passed by MapsActivity
        curr_word.text = extras.getString("currentWord") ?: "error"

        val time_taken_seconds = extras.getDouble("timeTaken")
        time_taken.text = "Time taken: $time_taken_seconds s"

        val distance_travelled_meters = extras.getInt("distanceTravelled")
        distance_travelled.text = "Distance travelled: $distance_travelled_meters m"

    }

    fun onAddWordClick(view : View){

        val confPopupBuilder = AlertDialog.Builder(this)
        confPopupBuilder.setTitle("Are you sure?")
        confPopupBuilder.setMessage("You will lose points if this is not a word!")

        confPopupBuilder.setPositiveButton("Yes"){ _, _ ->
            val resultIntent = Intent(this, ResultActivity::class.java)

            //Putting stuff into an existing key replaces anything there before
            //addWordResult already put in (in MapsActivity onStatsClick)

            resultIntent.putExtras(extras)
            startActivityForResult(resultIntent, resultResultCode) //onActivityResult is not called unless this is as well
        }

        confPopupBuilder.setNegativeButton("Not really"){ _ , _ ->
            //pass
        }

        val dialog = confPopupBuilder.create()
        dialog.show()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Check which request we're responding to
        if (requestCode == resultResultCode) { // Make sure the request was successful
            if (resultCode == Activity.RESULT_OK) {
                //Shove the intent back to MapsActivity for processing
                setResult(Activity.RESULT_OK, data) //activity will pass back RESULT_CANCELED if just pressing back button
                finish() //activity returns RESULT_OK to caller activity, bind onActivityResult here!

                //Just finish these activities, return to same instance of MapsActivity (with data from ResultActivity)
                //DON'T kill off MapsActivity (unless the game is done, using ProcessPhoenix) !!!
            }
        }

    }
}
