package com.example.youtubersapp.activity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.youtubersapp.R

class PerfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val tvNombreUsuario = findViewById<TextView>(R.id.tvNombreUsuario)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        // Recuperamos el usuario activo desde SharedPreferences
        val sharedPreferences = getSharedPreferences("YoutubersPrefs", MODE_PRIVATE)
        val usuario = sharedPreferences.getString("nombre_usuario", "Invitado")

        tvNombreUsuario.text = "Usuario conectado: $usuario"

        // Botón para volver a la pantalla principal
        btnVolver.setOnClickListener {
            finish() // Cierra esta actividad y regresa a la anterior
        }
    }
}