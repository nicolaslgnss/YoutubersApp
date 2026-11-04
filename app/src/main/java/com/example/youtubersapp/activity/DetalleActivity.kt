package com.example.youtubersapp.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.youtubersapp.R
import com.example.youtubersapp.databinding.ActivityDetalleBinding

class DetalleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Vinculamos el layout con ViewBinding
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Configuramos la Toolbar y activamos la flecha de retroceso
        setSupportActionBar(binding.toolbarDetalle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detalles del YouTuber"

        // 2. Recogemos todos los datos que llegan desde el adaptador
        val nombre = intent.getStringExtra("YOUTUBER_NOMBRE") ?: ""
        val categoria = intent.getStringExtra("YOUTUBER_CATEGORIA") ?: ""
        val suscriptores = intent.getStringExtra("YOUTUBER_SUSCRIPTORES") ?: ""
        val fotoResId = intent.getIntExtra("YOUTUBER_FOTO", 0)
        val canalUrl = intent.getStringExtra("YOUTUBER_URL") ?: ""
        val descripcion = intent.getStringExtra("YOUTUBER_DESCRIPCION") ?: ""
        val pais = intent.getStringExtra("YOUTUBER_PAIS") ?: ""
        val anio = intent.getStringExtra("YOUTUBER_ANIO") ?: ""

        // 3. Mostramos todos los datos en la interfaz
        binding.tvDetalleNombre.text = nombre
        binding.tvDetalleCategoria.text = categoria
        binding.tvDetalleSuscriptores.text = suscriptores
        binding.tvDetalleDescripcion.text = descripcion
        binding.tvDetallePais.text = "País: $pais"
        binding.tvDetalleAnio.text = "Creado en: $anio"

        if (fotoResId != 0) {
            binding.ivDetalleFoto.setImageResource(fotoResId)
        }

        // 4. Programamos el botón para abrir el canal oficial en YouTube
        binding.btnVisitarCanal.setOnClickListener {
            if (canalUrl.isNotEmpty()) {
                val intentUrl = Intent(Intent.ACTION_VIEW, Uri.parse(canalUrl))
                startActivity(intentUrl)
            }
        }

        // 5. Programamos el botón para compartir el canal
        binding.btnCompartirCanal.setOnClickListener {
            if (canalUrl.isNotEmpty()) {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "¡Hola! Te recomiendo este canal de YouTube de $nombre: $canalUrl")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, "Compartir canal vía")
                startActivity(shareIntent)
            }
        }
    }

    // 6. Hacemos que el botón de retroceso de la barra superior funcione
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    // 7. Inflamos el menú superior con la opción "Acerca de"
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // 8. Gestionamos el clic en la opción "Acerca de" con su animación
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}