package com.example.youtubersapp.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.youtubersapp.R

class DetalleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        val urlCanal = intent.getStringExtra("URL") ?: "https://www.youtube.com"

        // Abrir automáticamente o mediante un botón de forma nativa y segura
        val intentWeb = Intent(Intent.ACTION_VIEW, Uri.parse(urlCanal))
        startActivity(intentWeb)
        finish() // Cierra la actividad para volver limpia a la lista al salir
    }
}