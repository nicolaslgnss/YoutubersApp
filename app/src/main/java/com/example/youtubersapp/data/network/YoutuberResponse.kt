package com.example.youtubersapp.data.network

import com.google.gson.annotations.SerializedName

data class YoutuberResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("subscribers") val subscribers: String
)

