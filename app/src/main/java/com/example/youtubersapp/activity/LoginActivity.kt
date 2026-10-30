package com.example.youtubersapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.youtubersapp.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Referencias exactas a los elementos del XML
        val etNombreusuario = findViewById<EditText>(R.id.etNombreusuario)
        val btnEntrar = findViewById<Button>(R.id.btnEntrar)

        btnEntrar.setOnClickListener {
            val nombre = etNombreusuario.text.toString().trim()

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