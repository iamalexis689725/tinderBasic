package com.example.tindercarrerasuniversitarias

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CarrerasAdapter(
    private val carreras: MutableList<CarrerasUniversitarias>
) : RecyclerView.Adapter<CarrerasAdapter.CarreraViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarreraViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_carrera, parent, false)
        return CarreraViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarreraViewHolder, position: Int) {
        holder.bind(carreras[position])
    }

    override fun getItemCount(): Int {
        return carreras.size
    }

    class CarreraViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreCarrera: TextView = itemView.findViewById(R.id.textViewNombreCarrera)
        private val imagenCarrera: ImageView = itemView.findViewById(R.id.imageViewCarrera)

        fun bind(carrera: CarrerasUniversitarias) {
            nombreCarrera.text = carrera.nombre
            imagenCarrera.setImageResource(carrera.imagenes.first())
        }
    }
}
