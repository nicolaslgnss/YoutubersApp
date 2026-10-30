package com.example.youtubersapp.database

data class Youtuber(
    val nombre: String,
    val categoria: String,
    val suscriptores: String,
    val fotoResId: Int,       // URL de la imagen en formato texto
    val canalUrl: String      // URL del canal de YouTube
)