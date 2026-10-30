package com.example.youtubersapp.data.network

import retrofit2.Response
import retrofit2.http.GET


interface YoutuberApiService {
    @GET("youtubers.json")
    suspend fun getYoutubers(): Response<List<YoutuberResponse>>
}