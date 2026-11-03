package com.example.youtubersapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.youtubersapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    // Declaramos la variable de binding
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inflamos el layout utilizando ViewBinding
        binding = ActivityLoginBinding.inflate(layoutInflater)

        // 2. Establecemos la vista raíz en la actividad
        setContentView(binding.root)

        // 3. Configuramos la lógica del botón usando el binding (sin findViewById)
        binding.btnEntrar.setOnClickListener {
            val nombre = binding.etNombreusuario.text.toString().trim()

            if (nombre.isNotEmpty()) {
                // Si ha escrito un nombre, pasa a la MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Por favor, introduce tu nombre", Toast.LENGTH_SHORT).show()

            }
        }
    }
}