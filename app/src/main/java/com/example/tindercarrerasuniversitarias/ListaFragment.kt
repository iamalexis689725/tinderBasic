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

    private lateinit var viewModel: CarrerasViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CarrerasAdapter
    private var listaFavoritas: MutableList<CarrerasUniversitarias> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(CarrerasViewModel::class.java)

        recyclerView = view.findViewById(R.id.recyclerViewFavoritas)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = CarrerasAdapter(listaFavoritas)
        recyclerView.adapter = adapter


        cargarFavoritas()

        return view
    }

    private fun cargarFavoritas() {
        listaFavoritas.addAll(viewModel.likedCarreras.value ?: emptyList())
        adapter.notifyDataSetChanged()
    }
}
