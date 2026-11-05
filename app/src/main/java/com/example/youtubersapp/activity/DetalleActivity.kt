package com.example.youtubersapp.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.youtubersapp.databinding.ActivityDetalleBinding

class DetalleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Habilitar la barra de acción nativa para poder volver atrás si se desea
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detalle del Creador"

        // Recoger los datos enviados desde el adaptador mediante el Intent
        val nombre = intent.getStringExtra("nombre") ?: ""
        val categoria = intent.getStringExtra("categoria") ?: ""
        val suscriptores = intent.getStringExtra("suscriptores") ?: ""
        val descripcion = intent.getStringExtra("descripcion") ?: ""
        val pais = intent.getStringExtra("pais") ?: ""
        val anioCreacion = intent.getIntExtra("anioCreacion", 0)
        val url = intent.getStringExtra("url") ?: ""
        val imagenResId = intent.getIntExtra("imagenResId", 0)

        // Asignar los datos a las vistas usando el ID correcto ivFotoDetalle
        binding.tvNombreDetalle.text = nombre
        binding.tvCategoriaDetalle.text = categoria
        binding.tvSuscriptoresDetalle.text = suscriptores
        binding.tvPaisDetalle.text = pais
        binding.tvAnioDetalle.text = anioCreacion.toString()

        // Asignar la imagen de perfil real
        if (imagenResId != 0) {
            binding.ivFotoDetalle.setImageResource(imagenResId)
        }

        // Botón para abrir el canal de YouTube
        binding.btnVisitarCanal.setOnClickListener {
            if (url.isNotEmpty()) {
                val intentWeb = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intentWeb)
            }
        }

        // Botón para compartir el canal
        binding.btnCompartirCanal.setOnClickListener {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "¡Echa un vistazo al canal de YouTube de $nombre! $url")
                type = "text/plain"
            }
            startActivity(Intent.createChooser(shareIntent, "Compartir canal via"))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}