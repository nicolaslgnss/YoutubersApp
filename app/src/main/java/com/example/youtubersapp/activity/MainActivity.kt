package com.example.youtubersapp.activity

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubersapp.R
import com.example.youtubersapp.Youtuber
import com.example.youtubersapp.adapter.YoutuberAdapter
import com.example.youtubersapp.data.network.RetrofitClient
import com.example.youtubersapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listaYoutubers: List<Youtuber>
    private lateinit var adapter: YoutuberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        // 1. Activamos la pantalla de bienvenida oficial
        installSplashScreen()

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarMain)
        supportActionBar?.title = "YoutubersApp"

        val nombreUsuario = intent.getStringExtra("USER_NAME")
        if (!nombreUsuario.isNullOrEmpty()) {
            Toast.makeText(this, "¡Bienvenido, $nombreUsuario!", Toast.LENGTH_SHORT).show()
        }

        // Lista completa de los creadores
        listaYoutubers = listOf(
            Youtuber("Mikecrack", "Animación", "58.7M", "Canal de animación y gameplay", "España", 2015, "https://youtube.com/mikecrack", R.drawable.mikecrack, colorHex = "#FFEA00"),
            Youtuber("Rubius Z", "Gaming", "14.9M", "Gameplay y vlogs diarios", "España", 2006, "https://youtube.com/elrubius", R.drawable.rubius, colorHex = "#00E5FF"),
            Youtuber("Vegetta777", "Gaming", "34.8M", "Gameplays y series de Minecraft", "España", 2008, "https://youtube.com/vegetta777", R.drawable.vegetta, colorHex = "#7C4DFF"),
            Youtuber("AuronPlay", "Comedia", "29.1M", "Reacciones y humor", "España", 2006, "https://youtube.com/auronplay", R.drawable.auron, colorHex = "#FF9100"),
            Youtuber("Ibai Llanos", "Streaming", "17.4M", "Charlas, eventos y directos", "España", 2015, "https://youtube.com/ibai", R.drawable.ibai, colorHex = "#69F0AE"),
            Youtuber("TheGrefg", "Gaming", "19,9M", "Directos y Fortnite", "España", 2012, "https://youtube.com/thegrefg", R.drawable.grefg, colorHex = "#FF3D71"),
            Youtuber("DjMaRiiO", "Deportes", "10.3M", "Fútbol y EA Sports FC", "España", 2009, "https://youtube.com/djmariio", R.drawable.djmariio, colorHex = "#00B0FF"),
            Youtuber("Lpcast", "Gaming", "27.9K", "Contenido de videojuegos y Guías juegos", "España", 2018, "https://youtube.com/lpcast", R.drawable.lpcast, colorHex = "#E040FB"),
            Youtuber("tailon124 3", "Gaming", "386 subs", "Gameplay variados", "España", 2018, "https://youtube.com/tailon124", R.drawable.tailon, colorHex = "#1DE9B6"),
            Youtuber("nicolaslgnss", "Gaming", "154 subs", "Gaming y Streaming", "España", 2018, "https://youtube.com/nicolaslgnss", R.drawable.nicolas, colorHex = "#FFAB40"),
            Youtuber("TheNefor", "Tutoriales", "75 subs ", "Tutoriales y Gaming", "España", 2017, "https://youtube.com/thenefor", R.drawable.thenefor, colorHex = "#EEFF41"),
            Youtuber("tobbalink", "Gaming / FIFA", "3.58M", "Modo carrera, Ultimate Team y fútbol virtual", "España", 2012, "https://youtube.com/@tobbalink", R.drawable.tobbalink, colorHex = "#00E676"),
            Youtuber("pumsucor", "Gaming", "893K", "Gameplays y vídeos divertidos de fútbol", "España", 2006, "https://youtube.com/@pumuscor", R.drawable.pumuscor, colorHex = "#FF5722"),
            Youtuber("cacho01", "Fútbol / Gaming", "1.87M", "EA Sports FC, Ultimate Team y fútbol", "España", 2012, "https://youtube.com/@cachoo01", R.drawable.cachoo01, colorHex = "#FFD700"),
            Youtuber("miikelmst", "Gaming", "550K", "Retos, entretenimiento y gameplays de fútbol", "España", 2013, "https://youtube.com/@miikelmst", R.drawable.miikelmst, colorHex = "#00BCD4"),
            Youtuber("Illojuan", "Streaming / Variedad", "2.99M", "Directos divertidos, reacciones y juegos variados", "España", 2008, "https://youtube.com/@illojuan_", R.drawable.illojuan, colorHex = "#7C4DFF"),
            Youtuber("ElXokas", "Streaming / Opinión", "2.86M", "Charlas, directos y debate sobre actualidad", "España", 2007, "https://youtube.com/@elxokas", R.drawable.elxokas, colorHex = "#FF3D71"),
            Youtuber("bydiegox10", "Gaming", "972K", "Contenido de videojuegos y fútbol", "España", 2014, "https://youtube.com/@bydiegox10", R.drawable.bydiegox10, colorHex = "#00E5FF"),
            Youtuber("Real Madrid", "Deportes / Fútbol", "21.6M", "Canal oficial del club de fútbol", "España", 2006, "https://youtube.com/@realmadrid", R.drawable.realmadrid, colorHex = "#E50914"),
            Youtuber("Jordi Wild", "Podcast / Entrevistas", "7.41M", "The Wild Project y entrevistas de actualidad", "España", 2020, "https://youtube.com/@TheWildProject", R.drawable.jordiwild, colorHex = "#FF9100"),
            Youtuber("Vitu", "Gaming / Fútbol", "1.04M", "Modos carrera y directos de fútbol", "España", 2015, "https://youtube.com/@vituYT", R.drawable.vituyt, colorHex = "#69F0AE"),
            Youtuber("Papi Gavi", "Fútbol / Humor", "696K", "Reacciones de fútbol con mucho humor y pasión", "España", 2019, "https://youtube.com/@papigavi", R.drawable.papigavi, colorHex = "#FFEA00"),
            Youtuber("Spursito", "Fútbol / Entretenimiento", "1.62M", "Directos, fútbol y Kings League", "España", 2015, "https://youtube.com/@spursito", R.drawable.spursito, colorHex = "#00B0FF"),
            Youtuber("Esttik Foodie", "Retos / Comida", "1.04M", "Retos de comida y grandes burradas gastronómicas", "España", 2014, "https://youtube.com/@esttikPlus", R.drawable.esttik, colorHex = "#E040FB"),
            Youtuber("Rulas 47", "Gaming", "3.48M", "Gameplays y diversión", "España", 2017, "https://youtube.com/@rulas47", R.drawable.rulas47, colorHex = "#1DE9B6"),
            Youtuber("xBuyer", "Fútbol / Entretenimiento", "6.03M", "Retos, vlogs y fútbol con su hermano", "España", 2012, "https://youtube.com/@xbuyer", R.drawable.xbuyer, colorHex = "#FFAB40"),
            Youtuber("Zona Gemelos", "Entretenimiento", "948K", "Vlogs y retos muy divertidos", "España", 2018, "https://youtube.com/@zonagemelos", R.drawable.zonagemelos, colorHex = "#EEFF41"),
            Youtuber("Ampeter", "Gaming", "6.08M", "Fortnite, Call of Duty y gameplays diarios", "España", 2012, "https://youtube.com/@ampeter", R.drawable.ampeter, colorHex = "#8BC34A"),
            Youtuber("Agustin51 WORLD", "Gaming", "271K", "Juegos Variados y mucho humor", "España", 2022, "https://youtube.com/@agustinWORLD", R.drawable.agustin51, colorHex = "#FF9800"),
            Youtuber("Peereira7", "Gaming / Fútbol", "857K", "Juegos variados y fortnite", "España", 2017, "https://youtube.com/@peereira", R.drawable.peereira7, colorHex = "#607D8B")
        )

        // Configurar el RecyclerView con el adaptador
        adapter = YoutuberAdapter(listaYoutubers)
        binding.recyclerViewYoutubers.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewYoutubers.adapter = adapter

        // Llamamos a la API de forma segura al abrir la pantalla
        cargarYoutubersDesdeApi()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        // Configurar el buscador de la barra superior
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as? SearchView

        // Cambiar el color del texto introducido por el usuario a blanco
        val searchEditTextId = searchView?.context?.resources?.getIdentifier("android:id/search_src_text", null, null) ?: 0
        if (searchEditTextId != 0) {
            val searchEditText = searchView?.findViewById<EditText>(searchEditTextId)
            searchEditText?.setTextColor(Color.WHITE)
            searchEditText?.setHintTextColor(Color.LTGRAY)
        }

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filtrarLista(newText.orEmpty())
                return true
            }
        })

        return true
    }

    // Función para filtrar los elementos en tiempo real
    private fun filtrarLista(texto: String) {
        val textoMinuscula = texto.lowercase()
        val listaFiltrada = listaYoutubers.filter { youtuber ->
            youtuber.nombre.lowercase().contains(textoMinuscula) ||
                    youtuber.categoria.lowercase().contains(textoMinuscula)
        }
        binding.recyclerViewYoutubers.adapter = YoutuberAdapter(listaFiltrada)
    }

    // Función segura para manejar la red y llamadas con Retrofit
    private fun cargarYoutubersDesdeApi() {
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.getYoutubers()

                if (response.isSuccessful) {
                    val listaApi = response.body()
                    Toast.makeText(this@MainActivity, "¡Datos de la API cargados con éxito!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "Error en el servidor (Código: ${response.code()})", Toast.LENGTH_SHORT).show()
                }

            } catch (e: IOException) {
                Toast.makeText(this@MainActivity, "Error de red. Comprueba tu conexión a internet.", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Ocurrió un error inesperado: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            R.id.action_about -> {
                Toast.makeText(this, "YoutubersApp - Versión 1.0", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_logout -> {
                AlertDialog.Builder(this)
                    .setTitle("Cerrar sesión")
                    .setMessage("¿Estás seguro de que quieres salir de la aplicación?")
                    .setPositiveButton("Sí") { dialog, _ ->
                        // Borramos los datos de SharedPreferences al cerrar sesión
                        val sharedPreferences = getSharedPreferences("SesionApp", MODE_PRIVATE)
                        sharedPreferences.edit().clear().apply()

                        val intent = Intent(this, LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()
                        dialog.dismiss()
                    }
                    .setNegativeButton("Cancelar") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}