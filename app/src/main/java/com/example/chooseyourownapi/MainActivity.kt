package com.example.chooseyourownapi

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers



class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.pokemonRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PokemonAdapter()
        recyclerView.adapter = adapter

        loadRandomPokemon()
    }

    private fun loadRandomPokemon() {
        for (i in 1..10) { //Load 10 random Pokemon (you can adjust the number)
        val randomPokemonId = (1..898).random()
        val apiUrl = "https://pokeapi.co/api/v2/pokemon/$randomPokemonId/"

        val client = AsyncHttpClient()
        client.get(apiUrl, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                val name = json.jsonObject.getString("name")
                val imageURL = json.jsonObject.getJSONObject("sprites")
                    .getString("front_default")
                val abilities = json.jsonObject.getJSONArray("abilities")

                val abilityNames = (0 until abilities.length()).map {
                    abilities.getJSONObject(it).getJSONObject("ability").getString("name")
                }

                val pokemonEntry = PokemonEntry(name, imageURL, abilityNames.joinToString(", "))

                adapter.addPokemon(pokemonEntry)
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


    data class PokemonEntry(
        val name: String,
        val imageURL: String,
        val ability: String
    )}


