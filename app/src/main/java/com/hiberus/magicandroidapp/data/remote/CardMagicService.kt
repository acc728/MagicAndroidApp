package com.hiberus.magicandroidapp.data.remote

import com.hiberus.magicandroidapp.model.Card
import retrofit2.http.GET
import retrofit2.http.Query

interface CardMagicService {

    @GET("cards/random")
    suspend fun getRandomCard(): Card

    @GET("cards/autocomplete")
    suspend fun getCardsAutocomplete(@Query("q") cardName: String): List<String>

}