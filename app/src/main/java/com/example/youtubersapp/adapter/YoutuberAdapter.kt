package com.example.youtubersapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubersapp.Youtuber
import com.example.youtubersapp.activity.DetalleActivity
import com.example.youtubersapp.databinding.ItemYoutuberBinding

class YoutuberAdapter(private var listaYoutubers: List<Youtuber>) :
    RecyclerView.Adapter<YoutuberAdapter.YoutuberViewHolder>() {

    class YoutuberViewHolder(val binding: ItemYoutuberBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutuberViewHolder {
        val binding = ItemYoutuberBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return YoutuberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: YoutuberViewHolder, position: Int) {
        var youtuber = listaYoutubers[position]

        // Asignar los datos a las vistas de la tarjeta
        holder.binding.tvNombre.text = youtuber.nombre
        holder.binding.tvNombre.setTextColor(android.graphics.Color.parseColor("#FFFFFF"))

        holder.binding.tvCategoria.text = youtuber.categoria
        holder.binding.tvSuscriptores.text = youtuber.suscriptores
        holder.binding.ivAvatar.setImageResource(youtuber.imagenResId)

        // APLICAR EL COLOR FIJO AQUÍ:
        try {
            holder.binding.tvCategoria.setTextColor(android.graphics.Color.parseColor(youtuber.colorHex))
        } catch (e: Exception) {
            // Por si hay algún error de formato, usa un verde por defecto
            holder.binding.tvCategoria.setTextColor(android.graphics.Color.parseColor("#00E676"))
        }

        // Gestionar el icono de favorito según su estado booleano
        if (youtuber.esFavorito) {
            holder.binding.btnFavorito.setImageResource(android.R.drawable.btn_star_big_on)
        } else {
            holder.binding.btnFavorito.setImageResource(android.R.drawable.btn_star_big_off)
        }

        // Evento de clic en el botón de favorito para alternar su estado
        holder.binding.btnFavorito.setOnClickListener {
            youtuber.esFavorito = !youtuber.esFavorito
            notifyItemChanged(position)
        }

        // Evento para abrir la pantalla de detalle al hacer clic en la tarjeta
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetalleActivity::class.java).apply {
                putExtra("nombre", youtuber.nombre)
                putExtra("categoria", youtuber.categoria)
                putExtra("suscriptores", youtuber.suscriptores)
                putExtra("descripcion", youtuber.descripcion)
                putExtra("pais", youtuber.pais)
                putExtra("anioCreacion", youtuber.anioCreacion)
                putExtra("url", youtuber.canalUrl)
                putExtra("imagenResId", youtuber.imagenResId)
            }
            context.startActivity(intent)
        }

        // --- ANIMACIÓN DE TRANSICIÓN AL APARECER CADA TARJETA ---
        val animation = android.view.animation.AnimationUtils.loadAnimation(
            holder.itemView.context,
            com.example.youtubersapp.R.anim.item_animation_fall_down
        )
        holder.itemView.startAnimation(animation)
    }

    override fun getItemCount(): Int = listaYoutubers.size
}