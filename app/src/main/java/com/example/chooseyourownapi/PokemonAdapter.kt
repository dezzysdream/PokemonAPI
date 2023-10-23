package com.example.chooseyourownapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
    private val pokemonList = mutableListOf<MainActivity.PokemonEntry>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return PokemonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    fun addPokemon(pokemon: MainActivity.PokemonEntry) {
        pokemonList.add(pokemon)
        notifyItemInserted(pokemonList.size - 1)
    }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val pokemonImage: ImageView = itemView.findViewById(R.id.pokemonImage)
        private val pokemonName: TextView = itemView.findViewById(R.id.pokemonName)
        private val pokemonAbility: TextView = itemView.findViewById(R.id.pokemonAbility)

        fun bind(pokemon: MainActivity.PokemonEntry) {
            Glide.with(itemView.context)
                .load(pokemon.imageURL)
                .fitCenter()
                .into(pokemonImage)

            pokemonName.text = "Name: ${pokemon.name}"
            pokemonAbility.text = "Ability: ${pokemon.ability}"
        }
    }
}
