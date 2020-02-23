package com.sfucsss.mountainmadness2020.bathroom

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_stats.*

class StatsActivity : AppCompatActivity() {

    var addWordResult = false //True for good result, false for bad result
    lateinit var extras : Bundle

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

            //TODO: if word in dict, good result string, else bad result string

            //Change the bundled stuf; prep it for passing
            //Putting stuff into an existing key replaces anything there before
            extras.putBoolean("addWordResult", addWordResult)

            resultIntent.putExtras(extras)
            startActivity(resultIntent)

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        confPopupBuilder.setNegativeButton("Not really"){ _ , _ ->
            //pass
        }

        val dialog = confPopupBuilder.create()
        dialog.show()

    }
}
