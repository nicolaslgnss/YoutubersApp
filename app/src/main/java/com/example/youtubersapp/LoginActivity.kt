package com.example.youtubersapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etNombreUsuario = findViewById<EditText>(R.id.etNombreUsuario)
        val btnEntrar = findViewById<Button>(R.id.btnEntrar)

        // 1. Comprobar si ya hay una sesión guardada
        val sharedPreferences = getSharedPreferences("SesionApp", Context.MODE_PRIVATE)
        val nombreGuardado = sharedPreferences.getString("nombre_usuario", null)

        if (nombreGuardado != null) {
            // Si ya hay nombre, saltamos directamente a la pantalla principal
            irAMainActivity()
        }

        // 2. Evento del botón para guardar el nombre y entrar
        btnEntrar.setOnClickListener {
            val nombre = etNombreUsuario.text.toString()

            if (nombre.isNotEmpty()) {
                // Guardamos el nombre en SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("nombre_usuario", nombre)
                editor.apply()

                irAMainActivity()
            } else {
                Toast.makeText(this, "Por favor, introduce tu nombre", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun irAMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Cerramos el login para que no se pueda volver atrás con el botón del móvil
    }
}