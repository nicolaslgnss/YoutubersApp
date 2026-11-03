package com.example.youtubersapp.activity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubersapp.R
import com.example.youtubersapp.adapter.YoutuberAdapter
import com.example.youtubersapp.database.Youtuber
import com.example.youtubersapp.data.network.RetrofitClient // Asegúrate de que coincida con tu paquete de red
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



class MainActivity : AppCompatActivity() {

    private lateinit var youtuberAdapter: YoutuberAdapter
    private val listaYoutubers = mutableListOf<Youtuber>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // --- 1. GESTIÓN DE SESIÓN CON SHAREDREFERENCES ---
        val sharedPreferences = getSharedPreferences("YoutubersPrefs", MODE_PRIVATE)
        var usuarioActual = sharedPreferences.getString("nombre_usuario", null)

        if (usuarioActual == null) {
            usuarioActual = "Nicolas"
            val editor = sharedPreferences.edit()
            editor.putString("nombre_usuario", usuarioActual)
            editor.apply()
        }

        // --- 2. MOSTRAR EL ALERTDIALOG DE BIENVENIDA AL INICIAR ---
        mostrarAlertaBienvenida(usuarioActual)

        // --- 3. CONFIGURACIÓN DEL RECYCLERVIEW ---
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewYoutubers)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inicializamos el adaptador con una lista mutable
        youtuberAdapter = YoutuberAdapter(listaYoutubers)
        recyclerView.adapter = youtuberAdapter

        // Cargamos los datos por defecto inicialmente o llamamos a Retrofit
        cargarListaLocal()

        // --- LLAMADA A RETROFIT EN SEGUNDO PLANO ---
        obtenerDatosConRetrofit()

        // --- 5. CONFIGURACIÓN DEL BOTÓN DE CERRAR SESIÓN ---
        val botonCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)
        botonCerrarSesion.setOnClickListener {
            finish()
        }
    }

    private fun cargarListaLocal() {
        listaYoutubers.clear()
        listaYoutubers.addAll(
            listOf(
                Youtuber("Mikecrack", "Gaming / Animación", "58.7 M", R.drawable.mikecrack, "https://www.youtube.com/Mikecrack"),
                Youtuber("Rubius Z", "Gameplay / Comedia", "14.9 M", R.drawable.rubius, "https://www.youtube.com/@elrubius"),
                Youtuber("Vegetta777", "Gaming / Minecraft", "34.8 M", R.drawable.vegetta, "https://www.youtube.com/vegetta777"),
                Youtuber("AuronPlay", "Comedia / Gaming", "29.2 M", R.drawable.auron, "https://www.youtube.com/@auronplay"),
                Youtuber("Ibai Llanos", "Streaming / Eventos", "17.4 M", R.drawable.ibai, "https://www.youtube.com/@IbaiLlanos"),
                Youtuber("TheGrefg", "Gaming / Directos", "19.9 M", R.drawable.grefg, "https://www.youtube.com/@TheGrefg"),
                Youtuber("DjMaRiiO", "Fútbol / Gaming", "10.3 M", R.drawable.djmariio, "https://www.youtube.com/@DjMaRiiO"),
                Youtuber("Lpcast", "Contenido / Gameplay", "27.9 K", R.drawable.lpcast, "https://www.youtube.com/@lpcast"),
                Youtuber("tailon124 3", "Gaming / Variedades", "386 subs", R.drawable.tailon, "https://www.youtube.com/@Tailon-wq9hv"),
                Youtuber("nicolaslgnss", "Gameplay / Streaming", "154 subs", R.drawable.nicolas, "https://www.youtube.com/@nicolaslgnss"),
                Youtuber("TheNefor", "Gameplay / Tutorial", "75 subs", R.drawable.thenefor, "https://www.youtube.com/@thenefor7219")
            )
        )
        youtuberAdapter.notifyDataSetChanged()
    }

    private fun obtenerDatosConRetrofit() {
        // Usamos lifecycleScope para realizar la petición HTTP de forma asíncrona
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                // Llamada a tu servicio Retrofit (Ajusta el nombre del método según tu interfaz ApiService)
                // val respuesta = RetrofitClient.instance.obtenerYoutubers()

                withContext(Dispatchers.Main) {
                    // Aquí procesarías la respuesta de la API cuando la conectes del todo
                    // if (respuesta.isNotEmpty()) { ... }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Si falla la red, la app sigue funcionando con la lista local sin cerrarse
            }
        }
    }

    private fun mostrarAlertaBienvenida(usuario: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¡Bienvenido, $usuario!")
        builder.setMessage("Sesión iniciada correctamente. Aplicación desarrollada para cumplir con los requisitos del módulo de Android.")
        builder.setIcon(android.R.drawable.ic_dialog_info)
        builder.setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }
}