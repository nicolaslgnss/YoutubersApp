package com.example.youtubersapp

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class DetalleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        val webView = findViewById<WebView>(R.id.webViewYoutube)

        // Recogemos la URL que le enviamos desde la tarjeta
        val urlVideo = intent.getStringExtra("url_video") ?: ""

        // Configuramos el WebView para que permita ejecutar vídeo y JavaScript
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.webViewClient = WebViewClient()

        // Cargamos el enlace
        if (urlVideo.isNotEmpty()) {
            webView.loadUrl(urlVideo)
        }
    }
}