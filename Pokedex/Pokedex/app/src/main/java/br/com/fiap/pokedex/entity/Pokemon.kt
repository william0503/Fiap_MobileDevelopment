package br.com.fiap.pokedex.entity

import com.google.gson.annotations.SerializedName

data class Pokemon(
    val name : String,
    var sprites : Sprites
)

data class Sprites (
    @SerializedName("front_default") val normal: String,
    @SerializedName("front_shiny") val shiny: String
)
