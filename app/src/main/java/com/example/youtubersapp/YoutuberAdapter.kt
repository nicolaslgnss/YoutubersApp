package com.example.youtubersapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class YoutuberAdapter(private val listaYoutubers: List<Youtuber>) :
    RecyclerView.Adapter<YoutuberAdapter.YoutuberViewHolder>() {

    class YoutuberViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivAvatar: ImageView = view.findViewById(R.id.ivAvatar)
        val tvNombre: TextView = view.findViewById(R.id.tvNombre)
        val tvCategoria: TextView = view.findViewById(R.id.tvCategoria)
        val tvSuscriptores: TextView = view.findViewById(R.id.tvSuscriptores)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutuberViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_youtuber, parent, false)
        return YoutuberViewHolder(view)
    }

    override fun onBindViewHolder(holder: YoutuberViewHolder, position: Int) {
        val youtuber = listaYoutubers[position]

        holder.tvNombre.text = youtuber.nombre
        holder.tvCategoria.text = youtuber.categoria
        holder.tvSuscriptores.text = "${youtuber.suscriptores} suscriptores"

        // Cargar la imagen directamente desde los recursos locales (drawable)
        holder.ivAvatar.setImageResource(youtuber.fotoResId)

        // Al hacer clic en la tarjeta, abre nuestra DetalleActivity con el WebView
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetalleActivity::class.java).apply {
                putExtra("url_video", youtuber.canalUrl)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listaYoutubers.size
}