package com.example.youtubersapp.activity

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.example.youtubersapp.databinding.ActivityDetalleBinding

class DetalleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Vinculamos el layout con ViewBinding
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Recogemos los datos que llegan desde el adaptador
        val urlVideo = intent.getStringExtra("URL_VIDEO")
        val nombreCanal = intent.getStringExtra("NOMBRE_CANAL")

        // 2. Mostramos el nombre del canal en el TextView
        if (!nombreCanal.isNullOrEmpty()) {
            binding.textNombreCanal.text = nombreCanal
        }

        // 3. Cargamos el vídeo en el VideoView
        if (!urlVideo.isNullOrEmpty()) {
            val uri = Uri.parse(urlVideo)
            binding.videoViewCanal.setVideoURI(uri)
        }

        // 4. Añadimos los controles multimedia al VideoView
        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoViewCanal)
        binding.videoViewCanal.setMediaController(mediaController)

        // 5. Reproducción automática al estar listo el vídeo
        binding.videoViewCanal.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.start()
        }

        binding.videoViewCanal.requestFocus()
    }
}

