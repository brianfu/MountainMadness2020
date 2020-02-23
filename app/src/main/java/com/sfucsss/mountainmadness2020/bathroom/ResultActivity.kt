package com.sfucsss.mountainmadness2020.bathroom

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    companion object { //static
        internal fun wordsStringBuilder(wordsFound : ArrayList<String>) : String{
            var res = "" //String concat works as normal
            res += "Words solved: ["

            for (word in wordsFound){
                res += " $word"
            }

            res += " ]"
            return res
        }
    }

    lateinit var extras : Bundle
    lateinit var wordsFound : ArrayList<String> //take this out, process it here, and shove it back in after
    var useLastLetter = false //init

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        extras =  this.intent.extras ?: Bundle() //else give empty bundle
        wordsFound = extras.getStringArrayList("wordsFound") ?: ArrayList<String>() //else give empty arraylist

        val wordResult = extras.getBoolean("addWordResult") //true if good result, false if bad result
        if(wordResult){
            result_string.text = "That is indeed a word!"

            //val wordToAdd = extras.getString("currentWord") ?: "Error"
            //wordsFound.add(wordToAdd) //already done earlier
        }else{
            result_string.text = "That isn't a word!"
            //todo: point losing logic here
        }

        val dictionary = Dict()
        val potentialNewWord = extras.getString("currentWord") ?: ""
        if (dictionary.isValid(potentialNewWord)){
            //This is needed since at this point, the new word would not be in wordsFound yet (but would be in allString())
            //Therefore, need to add here manually, but no need to shove this back into the returning intent
            wordsFound.add(potentialNewWord)
        }
        words_solved.text = wordsStringBuilder(wordsFound)

        val timeTaken = extras.getDouble("timeTaken")
        time_taken.text = "Time taken: $timeTaken s"

        val distanceTravelled = extras.getInt("distanceTravelled")
        distance_travelled.text = "Distance travelled: $distanceTravelled m"
    }

    fun onContLastLetterClick(view: View){
        useLastLetter = true
        //todo add contLastLetter onclick logic here

        mapIntenter() //this func finishes the activity, code after this point won't run
    }

    fun onContNoLastLetterClick(view: View){
        useLastLetter = false
        //todo add contNoLastLetter onclick logic here

        mapIntenter()
    }

    fun onEndButtonClick(view: View){
        val endIntent = Intent(this, EndActivity::class.java)

        bundleAdder() //just use default value for useLastLetter, we don't care

        endIntent.putExtras(extras)

        startActivity(endIntent)

        setResult(Activity.RESULT_OK, endIntent)
        finish() //can leave this in, in case player doesn't want to end after all (otherwise map activity will mess up)
    }


    private fun mapIntenter(){ //doesn't actually intent anything, just returns back to the original MapsActivity instance
        val MapIntent = Intent() //this is blank here, use it for data and return it back

        bundleAdder()

        MapIntent.putExtras(extras)

        setResult(Activity.RESULT_OK, MapIntent)
        finish() //"intent" back into the current MapsActivity, returning the extras bundle in the process
    }

    private fun bundleAdder(){ //puts stuff in extras
        //Put stuff in bundle
        extras.putBoolean("useLastLetter", useLastLetter) //Tells the map whether to use the last letter or not
        //extras.putStringArrayList("wordsFound", wordsFound) //gameManager in map does this
        //todo put more stuff in bundle
    }

}
