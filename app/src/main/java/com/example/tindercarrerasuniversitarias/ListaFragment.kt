package com.example.tindercarrerasuniversitarias

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaFragment : Fragment() {

    private lateinit var vistaModelo: CarrerasViewModel
    private lateinit var listaReciclaje: RecyclerView
    private lateinit var adaptador: CarrerasAdapter
    private var listaFavoritas: MutableList<CarrerasUniversitarias> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_lista, container, false)

        vistaModelo = ViewModelProvider(requireActivity()).get(CarrerasViewModel::class.java)

        listaReciclaje = vista.findViewById(R.id.recyclerViewFavoritas)
        listaReciclaje.layoutManager = LinearLayoutManager(requireContext())
        adaptador = CarrerasAdapter(listaFavoritas)
        listaReciclaje.adapter = adaptador

        cargarFavoritas()

        return vista
    }

    private fun cargarFavoritas() {
        listaFavoritas.addAll(vistaModelo.carrerasFavoritas.value ?: emptyList())
        adaptador.notifyDataSetChanged()
    }
}
