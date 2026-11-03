package com.example.youtubersapp.data.network

import com.google.gson.annotations.SerializedName

class YoutuberModel (
    @SerializedName("nombre") val nombre: String,
    @SerializedName("categoria") val categoria: String,
    @SerializedName("suscriptores") val suscriptores: String,
    @SerializedName("canalUrl") val canalUrl: String
)


