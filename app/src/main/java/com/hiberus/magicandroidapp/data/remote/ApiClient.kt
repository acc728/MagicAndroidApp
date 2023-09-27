package com.hiberus.magicandroidapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.scryfall.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}