package com.example.youtubersapp

data class Youtuber(
    val nombre: String,
    val categoria: String,
    val suscriptores: String,
    val descripcion: String,
    val pais: String,
    val anioCreacion: Int,
    val canalUrl: String,
    val imagenResId: Int,
    val colorHex: String ="#00E5FF",
    var esFavorito: Boolean = false
)