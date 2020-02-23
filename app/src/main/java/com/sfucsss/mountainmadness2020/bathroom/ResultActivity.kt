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

            val wordToAdd = extras.getString("currentWord") ?: "Error"
            wordsFound.add(wordToAdd)
        }else{
            result_string.text = "That isn't a word!"
            //todo: point losing logic here
        }

        words_solved.text = wordsStringBuilder(wordsFound)

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
        finish()
    }


    private fun mapIntenter(){
        val MapIntent = Intent(this, MapsActivity::class.java)

        bundleAdder()

        MapIntent.putExtras(extras)

        startActivity(MapIntent) //have to make the new instance, need the bundle

        setResult(Activity.RESULT_OK, MapIntent)
        finish()
    }

    private fun bundleAdder(){ //puts stuff in extras
        //Put stuff in bundle
        extras.putBoolean("useLastLetter", useLastLetter)
        extras.putStringArrayList("wordsFound", wordsFound)
        //todo put more stuff in bundle
    }

}
