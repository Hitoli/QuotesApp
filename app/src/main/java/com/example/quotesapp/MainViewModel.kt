package com.example.quotesapp

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.io.InputStream
import java.nio.charset.Charset

class MainViewModel(val context:Context):ViewModel() {
    private var quotesArray: Array<Quotesdata> = emptyArray()
    private var index=0;

    init {
        quotesArray =LoadQuotesFromArray()
    }

    private fun LoadQuotesFromArray(): Array<Quotesdata> {
        val inputStream = context.assets.open("quotes.json")
        val size:Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json =String(buffer,Charsets.UTF_8)
        val gson = Gson()
        return gson .fromJson(json,Array<Quotesdata>::class.java)
    }

    fun getQuotes() = quotesArray[index]
    fun getQuotesnext()=quotesArray[++index]
    fun getQuotesprevious()=quotesArray[--index]
}