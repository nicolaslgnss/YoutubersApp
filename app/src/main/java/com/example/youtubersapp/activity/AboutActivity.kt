package com.example.youtubersapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.youtubersapp.R

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        // Activamos la barra superior con título y botón de retroceso
        supportActionBar?.title = "Acerca de"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
        return true
    }
}