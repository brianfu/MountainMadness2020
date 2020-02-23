package com.sfucsss.mountainmadness2020.bathroom

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_end.*

class EndActivity : AppCompatActivity() {

    lateinit var extras : Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end)

        extras = this.intent.extras ?: Bundle()

        val wordsFound = extras.getStringArrayList("wordsFound") ?: ArrayList<String>()
        val wordsCount = wordsFound.count()
        titleText.text = "You solved $wordsCount words!"

        words_found.text = ResultActivity.wordsStringBuilder(wordsFound) //static

        val timeTaken = extras.getDouble("timeTaken")
        time_taken.text = "Time taken = $timeTaken s"

        val distanceTravelled = extras.getInt("distanceTravelled")
        distance_travelled.text = "Distance Travelled: $distanceTravelled m"
    }

    fun onPlayAgainClick(view: View){
        //Game is over, don't hand the bundle back!!!

        val newGameIntent = Intent(this, MainActivity::class.java)
        //Don't hand bundle back, make new one in new MapsActivity

        startActivity(newGameIntent)
        setResult(Activity.RESULT_OK, newGameIntent)
        finish()
    }
}
