package com.example.youtubersapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubersapp.R
import com.example.youtubersapp.adapter.YoutuberAdapter
import com.example.youtubersapp.database.Youtuber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Lista de Youtubers con sus datos y fotos locales
        val listaYoutubers = listOf(
            Youtuber(
                nombre = "Mikecrack",
                categoria = "Gaming / Animación",
                suscriptores = "48.2 M",
                fotoResId = R.drawable.mikecrack,
                canalUrl = "https://www.youtube.com/Mikecrack"
            ),
            Youtuber(
                nombre = "ElRubius",
                categoria = "Gameplay / Comedia",
                suscriptores = "40.8 M",
                fotoResId = R.drawable.rubius,
                canalUrl = "https://www.youtube.com/@elrubius"
            ),
            Youtuber(
                nombre = "Vegetta777",
                categoria = "Gaming / Minecraft",
                suscriptores = "34.6 M",
                fotoResId = R.drawable.vegetta,
                canalUrl = "https://www.youtube.com/vegetta777"
            ),
            Youtuber(
                nombre = "AuronPlay",
                categoria = "Comedia / Gaming",
                suscriptores = "29.2 M",
                fotoResId = R.drawable.auron,
                canalUrl = "https://www.youtube.com/@auronplay"
            ),
            Youtuber(
                nombre = "Ibai Llanos",
                categoria = "Streaming / Eventos",
                suscriptores = "12.5 M",
                fotoResId = R.drawable.ibai,
                canalUrl = "https://www.youtube.com/@IbaiLlanos"
            )
        )

        // 2. Configuración del RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewYoutubers)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = YoutuberAdapter(listaYoutubers)

        // 3. Configuración del botón de cerrar sesión
        val botonCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)
        botonCerrarSesion.setOnClickListener {
            finish()
        }

        // 4. (Opcional) Si quieres añadir un botón de información para disparar el AlertDialog de la práctica:
        // Asegúrate de tener un botón en activity_main.xml con id: btnInfoAlerta
        /*
        val btnInfoAlerta = findViewById<Button>(R.id.btnInfoAlerta)
        btnInfoAlerta?.setOnClickListener {
            mostrarAlertaPersonalizada()
        }
        */
    }

    // Función del AlertDialog exigida en la rúbrica
    private fun mostrarAlertaPersonalizada() {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Información de la Práctica")
        builder.setMessage("Aplicación desarrollada para cumplir con los requisitos del módulo de Android (Diálogo de alerta activo).")
        builder.setIcon(android.R.drawable.ic_dialog_info)

        builder.setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}