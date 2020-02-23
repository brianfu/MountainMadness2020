package com.sfucsss.mountainmadness2020.bathroom

import android.content.Context
import android.util.Log
import java.util.*
import kotlin.collections.ArrayList

class ReadFileClass(val context: Context) {

    val fileOutput = ArrayList<String>()

    fun scannerProcess() : Scanner{ //scanner for dict
        val scanner = Scanner(context.getResources().openRawResource(R.raw.dictionary))
        return scanner
    }

    fun readDictionaryFile(reader: Scanner){
        fileOutput.clear()
        //val reader = Scanner(resources.openRawResource(R.raw.grewords)) //The raw resource is opened as an InputStream
        while (reader.hasNextLine()){
            //"vilify \t slander; say evil things
            val line = reader.nextLine() ?: ""

            Log.d("reader", "the line is: $line") //debug log statement (check in logcat debug mode)

            fileOutput.add(line)
        }
    }

}