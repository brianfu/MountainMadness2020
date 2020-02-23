package com.sfucsss.mountainmadness2020.bathroom

import android.util.Log


//timer.start to start timer
//timer.stop to stop timer

class CountTimer(val totalSeconds : Long, intervalSeconds : Long = 1) {
//totalSeconds is total how many seconds allowed for game, can be set as high as max 32-bit signed if needed

    val millisInFuture : Long
    val countDownInterval : Long

    init{
        millisInFuture = totalSeconds * 1000
        countDownInterval = intervalSeconds * 1000
    }

    fun onTick(millisUntilFinished: Long) { //runs upon every tick
        Log.d("CountTimer", "Elapsed: " + (totalSeconds * 1000 - millisUntilFinished) / 1000)



    }

    fun onFinish() {
        Log.d("CountTimer", "Time's up!")
    }


}