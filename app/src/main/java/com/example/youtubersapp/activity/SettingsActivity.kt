package com.example.youtubersapp.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.youtubersapp.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Vinculamos el layout con ViewBinding
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuramos la Toolbar y el botón de retroceso
        setSupportActionBar(binding.toolbarSettings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Ajustes"

        // Gestionamos preferencias locales con SharedPreferences
        val sharedPreferences = getSharedPreferences("YoutubersPrefs", Context.MODE_PRIVATE)

        // Cargamos los estados guardados previamente (por defecto activados)
        val notisActivas = sharedPreferences.getBoolean("notificaciones", true)
        val modoOscuroActivo = sharedPreferences.getBoolean("modo_oscuro", true)

        binding.switchNotificaciones.isChecked = notisActivas
        binding.switchModoOscuro.isChecked = modoOscuroActivo

        // Guardar cambios al cambiar el switch de notificaciones
        binding.switchNotificaciones.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean("notificaciones", isChecked).apply()
        }

        // Guardar cambios al cambiar el switch de modo oscuro
        binding.switchModoOscuro.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean("modo_oscuro", isChecked).apply()
        }
    }

    // Permitir volver atrás con la flecha de la barra superior
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}