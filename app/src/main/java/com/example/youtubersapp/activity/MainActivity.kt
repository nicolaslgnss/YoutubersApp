package com.example.youtubersapp.activity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubersapp.R
import com.example.youtubersapp.adapter.YoutuberAdapter
import com.example.youtubersapp.database.Youtuber
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var youtuberAdapter: YoutuberAdapter
    private val listaYoutubers = mutableListOf<Youtuber>()
    private val listaOriginal = mutableListOf<Youtuber>() // Copia de respaldo para el buscador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // --- 1. GESTIÓN DE SESIÓN CON EL INTENT Y SHAREDREFERENCES ---
        val sharedPreferences = getSharedPreferences("YoutubersPrefs", MODE_PRIVATE)
        var usuarioActual = intent.getStringExtra("USER_NAME") ?: sharedPreferences.getString("nombre_usuario", null)

        if (usuarioActual == null) {
            usuarioActual = "Invitado"
        } else {
            val editor = sharedPreferences.edit()
            editor.putString("nombre_usuario", usuarioActual)
            editor.apply()
        }

        // --- 2. MOSTRAR EL ALERTDIALOG DE BIENVENIDA AL INICIAR ---
        mostrarAlertaBienvenida(usuarioActual)

        // --- 3. CONFIGURACIÓN DEL RECYCLERVIEW ---
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewYoutubers)
        recyclerView.layoutManager = LinearLayoutManager(this)

        youtuberAdapter = YoutuberAdapter(listaYoutubers)
        recyclerView.adapter = youtuberAdapter

        // Cargamos los datos por defecto inicialmente
        cargarListaLocal()

        // --- 4. LLAMADA A RETROFIT EN SEGUNDO PLANO ---
        obtenerDatosConRetrofit()

        // --- 5. CONFIGURACIÓN DEL BUSCADOR EN TIEMPO REAL ---
        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filtrarLista(newText)
                return true
            }
        })

        // --- 6. CONFIGURACIÓN DEL BOTÓN DE CERRAR SESIÓN ---
        val botonCerrarSesion = findViewById<Button>(R.id.btnCerrarSesión)
        botonCerrarSesion.setOnClickListener {
            finish()
        }
    }

    private fun cargarListaLocal() {
        listaYoutubers.clear()
        listaYoutubers.addAll(
            listOf(
                Youtuber("Mikecrack", "Gaming / Animación", "58.7 M", R.drawable.mikecrack, "https://www.youtube.com/Mikecrack", "Creador de contenido enfocado en videojuegos, animación y música con historias basadas en Minecraft y compas.", "España", "2015"),
                Youtuber("Rubius Z", "Gameplay / Comedia", "14.9 M", R.drawable.rubius, "https://www.youtube.com/@elrubius", "Uno de los pioneros de YouTube en España, famoso por sus gameplays, vlogs y reacciones de humor.", "España", "2011"),
                Youtuber("Vegetta777", "Gaming / Minecraft", "34.8 M", R.drawable.vegetta, "https://www.youtube.com/vegetta777", "Canal legendario de series de Minecraft, juegos cooperativos y aventuras narradas con un estilo inconfundible.", "España", "2008"),
                Youtuber("AuronPlay", "Comedia / Gaming", "29.2 M", R.drawable.auron, "https://www.youtube.com/@auronplay", "Canal de humor, crítica social cómica, reacciones y gameplays llenos de carisma.", "España", "2006"),
                Youtuber("Ibai Llanos", "Streaming / Eventos", "17.4 M", R.drawable.ibai, "https://www.youtube.com/@IbaiLlanos", "Caster, streamer y organizador de eventos masivos como La Velada del Año y la Kings League.", "España", "2014"),
                Youtuber("TheGrefg", "Gaming / Directos", "19.9 M", R.drawable.grefg, "https://www.youtube.com/@TheGrefg", "Creador especializado en Fortnite, gameplays extremos y eventos multitudinarios del mundo gamer.", "España", "2012"),
                Youtuber("DjMaRiiO", "Fútbol / Gaming", "10.3 M", R.drawable.djmariio, "https://www.youtube.com/@DjMaRiiO", "Referente mundial del contenido de EA Sports FC y fútbol real, partidos benéficos y vlogs.", "España", "2009"),
                Youtuber("Lpcast", "Contenido / Gameplay", "27.9 K", R.drawable.lpcast, "https://www.youtube.com/@lpcast", "Canal enfocado en gameplays variados, directos y guías .", "España", "2018"),
                Youtuber("tailon124 3", "Gaming / Variedades", "386 subs", R.drawable.tailon, "https://www.youtube.com/@Tailon-wq9hv", "Espacio dedicado al entretenimiento, videojuegos y variedades.", "España", "2020"),
                Youtuber("nicolaslgnss", "Gameplay / Streaming", "154 subs", R.drawable.nicolas, "https://www.youtube.com/@nicolaslgnss", "Proyecto personal de desarrollo y gaming enfocado en la creación de contenido.", "España", "2021"),
                Youtuber("TheNefor", "Gameplay / Tutorial", "75 subs", R.drawable.thenefor, "https://www.youtube.com/@thenefor7219", "Canal de tutoriales, guías y gameplays técnicos.", "España", "2022")
            )
        )

        // Guardamos una copia exacta en la lista original para que el buscador pueda filtrar y restaurar
        listaOriginal.clear()
        listaOriginal.addAll(listaYoutubers)

        youtuberAdapter.notifyDataSetChanged()
    }

    private fun filtrarLista(texto: String?) {
        listaYoutubers.clear()
        if (texto.isNullOrEmpty()) {
            listaYoutubers.addAll(listaOriginal)
        } else {
            val consulta = texto.lowercase()
            for (youtuber in listaOriginal) {
                if (youtuber.nombre.lowercase().contains(consulta) ||
                    youtuber.categoria.lowercase().contains(consulta)) {
                    listaYoutubers.add(youtuber)
                }
            }
        }
        youtuberAdapter.notifyDataSetChanged()
    }

    private fun obtenerDatosConRetrofit() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    // Procesar respuesta de red si se requiere
                }
            } catch (e: Exception) {
                e.printStackTrace()
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

    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                val intent = android.content.Intent(this, AboutActivity::class.java)
                startActivity(intent)
                // Aplicamos la animación fluida de transición
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}