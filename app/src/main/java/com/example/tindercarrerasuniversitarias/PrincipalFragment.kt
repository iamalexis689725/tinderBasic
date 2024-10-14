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

    private lateinit var viewModel: CarrerasViewModel
    private lateinit var imageView: ImageView
    private lateinit var lblNombre: TextView
    private lateinit var progressBars: List<ProgressBar>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_principal, container, false)

        imageView = view.findViewById(R.id.imageView)
        lblNombre = view.findViewById(R.id.lblNombre)
        progressBars = listOf(
            view.findViewById(R.id.progressBar3),
            view.findViewById(R.id.progressBar4),
            view.findViewById(R.id.progressBar5)
        )

        viewModel = ViewModelProvider(requireActivity()).get(CarrerasViewModel::class.java)
        view.findViewById<Button>(R.id.buttonLista).setOnClickListener {
            println("Lista de me gusta:")
            viewModel.likedCarreras.value?.forEach { likedCarrera ->
                println("Nombre: ${likedCarrera.nombre}")
                println("Imagen: ${likedCarrera.imagenes}")
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, ListaFragment())
                .addToBackStack(null)
                .commit()
        }
        // Observo cambios en la lista de carreras
        viewModel.carrerasList.observe(viewLifecycleOwner) { carrerasList ->
            // Actualizo el nombre y la imagen de la carrera actual
            val carreraActual = carrerasList[viewModel.currentIndex.value ?: 0]
            imageView.setImageResource(carreraActual.imagenes[viewModel.currentImageIndex.value ?: 0])
            lblNombre.text = carreraActual.nombre
            updateProgressBars()
        }

        // Observo cambios en el índice actual de imagen
        viewModel.currentImageIndex.observe(viewLifecycleOwner) {
            updateImage()
        }

        view.findViewById<Button>(R.id.buttonDislike).setOnClickListener {
            viewModel.nextCarrera()
        }


        view.findViewById<Button>(R.id.buttonLike).setOnClickListener {
            viewModel.likeCurrentCarrera()
            viewModel.nextCarrera()
        }


        imageView.setOnTouchListener { _, event ->
            handleTouch(event)
            true
        }

        return view
    }

    private fun updateImage() {
        val carreraActual = viewModel.carrerasList.value?.get(viewModel.currentIndex.value ?: 0)
        carreraActual?.let {
            imageView.setImageResource(it.imagenes[viewModel.currentImageIndex.value ?: 0])
            lblNombre.text = it.nombre
        }
    }

    private fun handleTouch(event: MotionEvent) {
        val width = imageView.width

        if (event.action == MotionEvent.ACTION_UP) {
            if (event.x < width / 2) {
                viewModel.previousImage()
            } else {
                viewModel.nextImage()
            }
            updateProgressBars()
        }
    }

    private fun updateProgressBars() {
        val currentImageIndex = viewModel.currentImageIndex.value ?: 0

        for (i in progressBars.indices) {
            if (i == currentImageIndex) {
                progressBars[i].progress = 100
            } else{
                progressBars[i].progress = 0
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PrincipalFragment()
    }
}

