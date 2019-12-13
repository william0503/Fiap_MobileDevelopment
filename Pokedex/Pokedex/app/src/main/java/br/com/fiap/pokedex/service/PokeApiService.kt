package br.com.fiap.pokedex.service

import br.com.fiap.pokedex.entity.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService{
    @GET("/api/v2/pokemon/{pokemonId}/")
    fun buscarPokemon(@Path("pokemonId") pokemonId: String): Call<Pokemon>
}