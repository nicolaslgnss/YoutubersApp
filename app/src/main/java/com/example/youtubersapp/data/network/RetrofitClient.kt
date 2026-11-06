package com.example.youtubersapp.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val instance: YoutuberApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://tu-url-de-la-api.com/") // Reemplaza con tu URL base real
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YoutuberApiService::class.java)
    }
}