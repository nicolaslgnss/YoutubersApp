package com.example.youtubersapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Configurar la lista de Youtubers (lo que ya tenías)
        val listaYoutubers = listOf(
            Youtuber(
                nombre = "Mikecrack",
                categoria = "Gaming / Minecraft",
                suscriptores = "58 M",
                fotoResId = R.drawable.mikecrack,
                canalUrl = "https://www.youtube.com/embed/g6qG0jj12o4"
            ),
            Youtuber(
                nombre = "ElRubius",
                categoria = "Gameplay / Comedia",
                suscriptores = "40.8 M",
                fotoResId = R.drawable.rubius,
                canalUrl = "https://www.youtube.com/embed/dQw4w9WgXcQ"
            ),
            Youtuber(
                nombre = "Vegetta777",
                categoria = "Gaming / Minecraft",
                suscriptores = "34.6 M",
                fotoResId = R.drawable.vegetta,
                canalUrl = "https://www.youtube.com/embed/dQw4w9WgXcQ"
            ),
            Youtuber(
                nombre = "AuronPlay",
                categoria = "Comedia / Gaming",
                suscriptores = "29.1 M",
                fotoResId = R.drawable.auron,
                canalUrl = "https://www.youtube.com/embed/dQw4w9WgXcQ"
            ),
            Youtuber(
                nombre = "Ibai Llanos",
                categoria = "Entretenimiento / Eventos",
                suscriptores = "17.4 M",
                fotoResId = R.drawable.ibai,
                canalUrl = "https://www.youtube.com/embed/dQw4w9WgXcQ"
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewYoutubers)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = YoutuberAdapter(listaYoutubers)

        // 2. Configurar el botón de Cerrar Sesión con el AlertDialog
        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)
        btnCerrarSesion.setOnClickListener {
            mostrarDialogoSalida()
        }
    }

    private fun mostrarDialogoSalida() {
        // Creamos el AlertDialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Cerrar sesión")
        builder.setMessage("¿Estás seguro de que quieres cerrar sesión?")

        // Botón Positivo (Sí)
        builder.setPositiveButton("Sí") { _, _ ->
            // Borramos el usuario de SharedPreferences
            val sharedPreferences = getSharedPreferences("SesionApp", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear() // Borra los datos guardados
            editor.apply()

            // Volvemos a la pantalla de Login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Botón Negativo (No)
        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }

        // Mostramos el diálogo en pantalla
        val dialogo = builder.create()
        dialogo.show()
    }
}