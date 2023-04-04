package com.example.quotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.quotesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var mainviewmodel:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainviewmodel = ViewModelProvider(this, MainViewModelFactory(applicationContext)).get(MainViewModel::class.java)
        SetQuotes(mainviewmodel.getQuotes())
        binding.previousButton.setOnClickListener {
            onClickbutton()
        }
        binding.nextButton.setOnClickListener {
            onClicknextButton()
        }
        binding.QuotesShare.setOnClickListener{
            onClickShare()
        }
    }

    private fun onClickShare() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, mainviewmodel.getQuotes().text)
        startActivity(intent)
    }

    fun SetQuotes(quote:Quotesdata){

        binding.Quotesitis.text =quote.text
        binding.QuotesAuthor.text = quote.author
       // Log.e("QUOTES",quote.text.toString())
            //Log.e("AUTHHOR",quote.author.toString())
    }
    fun onClickbutton(){
        SetQuotes(mainviewmodel.getQuotesprevious())
    }
    fun onClicknextButton(){
        SetQuotes(mainviewmodel.getQuotesnext())
    }

}