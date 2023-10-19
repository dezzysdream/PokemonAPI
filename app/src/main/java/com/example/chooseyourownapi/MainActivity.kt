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
    val randomPokemonId = (1..898).random()

    val client = AsyncHttpClient()
    val apiUrl = "https://pokeapi.co/api/v2/pokemon/$randomPokemonId/"

    client.get(apiUrl, object : JsonHttpResponseHandler() {
        override fun onSuccess(statusCode: Int, headers: Headers, json:JSON) {
            Log.d("poke", "response successful$json")

            val imageURL = json.jsonObject.getJSONObject("sprites")
                .getString("front_default")
            Log.d("pokeImageURL", "poke image URL: $imageURL")

            pokeImageURL = imageURL
        }

        override fun onFailure(
            statusCode: Int,
            headers: Headers?,
            errorResponse: String,
            throwable: Throwable?
        ) {
            Log.d("poke Error", "Error: $errorResponse")
        }
    })
    }


}