package com.example.tindercarrerasuniversitarias

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class PrincipalFragment : Fragment() {

    private lateinit var vistaModelo: CarrerasViewModel
    private lateinit var imagenCarrera: ImageView
    private lateinit var etiquetaNombre: TextView
    private lateinit var barrasProgreso: List<ProgressBar>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_principal, container, false)

        imagenCarrera = vista.findViewById(R.id.imageView)
        etiquetaNombre = vista.findViewById(R.id.lblNombre)
        barrasProgreso = listOf(
            vista.findViewById(R.id.progressBar3),
            vista.findViewById(R.id.progressBar4),
            vista.findViewById(R.id.progressBar5)
        )

        vistaModelo = ViewModelProvider(requireActivity()).get(CarrerasViewModel::class.java)
        vista.findViewById<Button>(R.id.buttonLista).setOnClickListener {
            println("Lista de me gusta:")
            vistaModelo.carrerasFavoritas.value?.forEach { carreraFavorita ->
                println("Nombre: ${carreraFavorita.nombre}")
                println("Imagen: ${carreraFavorita.imagenes}")
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, ListaFragment())
                .addToBackStack(null)
                .commit()
        }

        // Observo cambios en la lista de carreras
        vistaModelo.listaCarreras.observe(viewLifecycleOwner) { listaCarreras ->
            // Actualizo el nombre y la imagen de la carrera actual
            val carreraActual = listaCarreras[vistaModelo.indiceActual.value ?: 0]
            imagenCarrera.setImageResource(carreraActual.imagenes[vistaModelo.indiceImagenActual.value ?: 0])
            etiquetaNombre.text = carreraActual.nombre
            actualizarBarrasProgreso()
        }

        // Observo cambios en el índice actual de imagen
        vistaModelo.indiceImagenActual.observe(viewLifecycleOwner) {
            actualizarImagen()
        }

        vista.findViewById<Button>(R.id.buttonDislike).setOnClickListener {
            vistaModelo.siguienteCarrera()
        }

        vista.findViewById<Button>(R.id.buttonLike).setOnClickListener {
            vistaModelo.meGustaCarreraActual()
            vistaModelo.siguienteCarrera()
        }

        imagenCarrera.setOnTouchListener { _, event ->
            manejarToque(event)
            true
        }

        return vista
    }

    private fun actualizarImagen() {
        val carreraActual = vistaModelo.listaCarreras.value?.get(vistaModelo.indiceActual.value ?: 0)
        carreraActual?.let {
            imagenCarrera.setImageResource(it.imagenes[vistaModelo.indiceImagenActual.value ?: 0])
            etiquetaNombre.text = it.nombre
        }
    }

    private fun manejarToque(evento: MotionEvent) {
        val ancho = imagenCarrera.width

        if (evento.action == MotionEvent.ACTION_UP) {
            if (evento.x < ancho / 2) {
                vistaModelo.imagenAnterior()
            } else {
                vistaModelo.siguienteImagen()
            }
            actualizarBarrasProgreso()
        }
    }

    private fun actualizarBarrasProgreso() {
        val indiceImagenActual = vistaModelo.indiceImagenActual.value ?: 0

        for (i in barrasProgreso.indices) {
            if (i == indiceImagenActual) {
                barrasProgreso[i].progress = 100
            } else {
                barrasProgreso[i].progress = 0
            }
        }
    }

    companion object {
        @JvmStatic
        fun nuevaInstancia() = PrincipalFragment()
    }
}
