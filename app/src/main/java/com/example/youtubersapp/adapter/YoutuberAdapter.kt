package com.example.youtubersapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubersapp.R
import com.example.youtubersapp.activity.DetalleActivity
import com.example.youtubersapp.database.Youtuber

class YoutuberAdapter(private val listaYoutubers: List<Youtuber>) :
    RecyclerView.Adapter<YoutuberAdapter.YoutuberViewHolder>() {

    class YoutuberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFoto: ImageView = itemView.findViewById(R.id.ivAvatar)
        val txtNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val txtCategoria: TextView = itemView.findViewById(R.id.tvCategoria)
        val txtSuscriptores: TextView = itemView.findViewById(R.id.tvSuscriptores)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YoutuberViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_youtuber, parent, false)
        return YoutuberViewHolder(vista)
    }

    override fun onBindViewHolder(holder: YoutuberViewHolder, position: Int) {
        val youtuber = listaYoutubers[position]
        holder.txtNombre.text = youtuber.nombre
        holder.txtCategoria.text = youtuber.categoria
        holder.txtSuscriptores.text = youtuber.suscriptores
        holder.imgFoto.setImageResource(youtuber.fotoResId)

        // Al hacer clic en la tarjeta, abrimos la DetalleActivity pasando los datos
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetalleActivity::class.java).apply {
                putExtra("YOUTUBER_NOMBRE", youtuber.nombre)
                putExtra("YOUTUBER_CATEGORIA", youtuber.categoria)
                putExtra("YOUTUBER_SUSCRIPTORES", youtuber.suscriptores)
                putExtra("YOUTUBER_FOTO", youtuber.fotoResId)
                putExtra("YOUTUBER_URL", youtuber.canalUrl)
                putExtra("YOUTUBER_DESCRIPCION", youtuber.descripcion)
                putExtra("YOUTUBER_PAIS", youtuber.pais)
                putExtra("YOUTUBER_ANIO", youtuber.anioCreacion)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listaYoutubers.size
}
