package com.example.youtubersapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.youtubersapp.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 1. Enlazamos los elementos de la interfaz XML con sus tipos correctos
        val btnEntrar = findViewById<Button>(R.id.btnEntrar)
        val tilNombre = findViewById<TextInputLayout>(R.id.tilNombreUsuario)
        val etNombre = findViewById<TextInputEditText>(R.id.etNombreUsuario)

        // 2. Programamos la acción al hacer clic en el botón "Entrar"
        btnEntrar.setOnClickListener {
            val nombreUsuario = etNombre.text.toString().trim()

            // 3. Validamos si el campo está vacío
            if (nombreUsuario.isEmpty()) {
                tilNombre.error = "!Debes escribir un nombre para continuar!"
            } else {
                tilNombre.error = null // Limpiamos el error si todo está correcto

                // --- Guardar sesión con SHAREDREFERENCES ---
                val sharedPreferences = getSharedPreferences("SesionApp", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("USER_NAME", nombreUsuario)
                editor.apply()


                // 4. Creamos el Intent para saltar a la MainActivity enviando el nombre
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("USER_NAME", nombreUsuario)
                }
                startActivity(intent)

                // Cerramos la pantalla de login para que al pulsar atrás no vuelva aquí
                finish()
            }
        }
    }
}