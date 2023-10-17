package com.example.chooseyourownapi

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers



class MainActivity : AppCompatActivity() {
    private var pokeImageURL = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getPokeImageURL()
        val myButton: Button = findViewById(R.id.pokeButton)
        val myImage: ImageView = findViewById(R.id.Pikachu)

        getNextImage(myButton, myImage)

    }



    private fun getNextImage(button: Button, imageView: ImageView) {

        button.setOnClickListener {
            getPokeImageURL()

            Glide.with(this)
                .load(pokeImageURL)
                .fitCenter()
                .into(imageView)
        }

    }

private fun getPokeImageURL() {
        val client = AsyncHttpClient()
        client.get("https://pokeapi.co/api/v2/pokemon/ditto", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                // Initialize 'pokeImageURL' using data from the JSON response
                val pokeImageURL = json.jsonObject.getString("message")
                Log.d("Poke", "response successful$pokeImageURL")

                // Now, you can use 'pokeImageURL' or pass it to another function
            }

            override fun onFailure(statusCode: Int, headers: Headers?, errorResponse: String, throwable: Throwable?) {
                Log.d("Poke Error", errorResponse)
            }
        })
    }


}