package com.example.tindercarrerasuniversitarias

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CarrerasViewModel : ViewModel() {
    private val _listaCarreras = MutableLiveData<MutableList<CarrerasUniversitarias>>()
    val listaCarreras: LiveData<MutableList<CarrerasUniversitarias>> get() = _listaCarreras

    private val _indiceActual = MutableLiveData<Int>()
    val indiceActual: LiveData<Int> get() = _indiceActual

    private val _indiceImagenActual = MutableLiveData<Int>()
    val indiceImagenActual: LiveData<Int> get() = _indiceImagenActual

    // Lista para guardar las carreras "me gusta"
    private val _carrerasFavoritas = MutableLiveData<MutableList<CarrerasUniversitarias>>(mutableListOf())
    val carrerasFavoritas: LiveData<MutableList<CarrerasUniversitarias>> get() = _carrerasFavoritas

    init {
        _listaCarreras.value = mutableListOf(
            CarrerasUniversitarias("Administración", mutableListOf(R.drawable.administracion1, R.drawable.administracion2, R.drawable.administracion3)),
            CarrerasUniversitarias("Ing. Comercial", mutableListOf(R.drawable.comercial1, R.drawable.comercial2, R.drawable.comercial3)),
            CarrerasUniversitarias("Comunicación Social", mutableListOf(R.drawable.comunicacion1, R.drawable.comunicacion2, R.drawable.comunicacion3)),
            CarrerasUniversitarias("Contaduría", mutableListOf(R.drawable.contaduria1, R.drawable.contaduria2, R.drawable.contaduria3)),
            CarrerasUniversitarias("Derecho", mutableListOf(R.drawable.derecho1, R.drawable.derecho2, R.drawable.derecho3)),
            CarrerasUniversitarias("Ing. Financiera", mutableListOf(R.drawable.financiera1, R.drawable.financiera2, R.drawable.financiera3)),
            CarrerasUniversitarias("Fisioterapia", mutableListOf(R.drawable.fisio1, R.drawable.fisio2, R.drawable.fisio3)),
            CarrerasUniversitarias("Marketing", mutableListOf(R.drawable.marketing1, R.drawable.marketing2, R.drawable.marketing3)),
            CarrerasUniversitarias("Nutrición", mutableListOf(R.drawable.nutricion1, R.drawable.nutricion2, R.drawable.nutricion3)),
            CarrerasUniversitarias("Psicología", mutableListOf(R.drawable.psicologia1, R.drawable.psicologia2, R.drawable.psicologia3)),
            CarrerasUniversitarias("Psicopedagogía", mutableListOf(R.drawable.psicopedagogia1, R.drawable.psicopedagogia2, R.drawable.psicopedagogia3)),
            CarrerasUniversitarias("Redes", mutableListOf(R.drawable.redes1, R.drawable.redes2, R.drawable.redes3)),
            CarrerasUniversitarias("Relaciones Internacionales", mutableListOf(R.drawable.relacionesinternacionales1, R.drawable.relacionesinternacionales2, R.drawable.relacionesinternacionales3)),
            CarrerasUniversitarias("Relaciones Públicas", mutableListOf(R.drawable.relacionespublicas1, R.drawable.relacionespublicas2, R.drawable.relacionespublicas3)),
            CarrerasUniversitarias("Sistemas", mutableListOf(R.drawable.sistemas1, R.drawable.sistemas2, R.drawable.sistemas3)),
            CarrerasUniversitarias("Turismo", mutableListOf(R.drawable.turismo1, R.drawable.turismo2, R.drawable.turismo3))
        )

        // Inicializo el índice actual y el índice de imagen actual
        _indiceActual.value = 0
        _indiceImagenActual.value = 0

        // Inicializo la lista de carreras favoritas
        _carrerasFavoritas.value = mutableListOf()
    }

    fun meGustaCarreraActual() {
        val carreraActual = _listaCarreras.value?.get(_indiceActual.value ?: 0)
        carreraActual?.let {
            val listaActualizada = _carrerasFavoritas.value?.toMutableList() ?: mutableListOf()
            if (!listaActualizada.contains(it)) {
                listaActualizada.add(it)
                _carrerasFavoritas.value = listaActualizada
            }
        }
        println(carrerasFavoritas.value)
    }

    fun siguienteCarrera() {
        val indiceActualValor = _indiceActual.value ?: 0
        if (indiceActualValor < _listaCarreras.value!!.size - 1) {
            _indiceActual.value = indiceActualValor + 1
            _indiceImagenActual.value = 0
        }
    }

    fun siguienteImagen() {
        val carreraActual = _listaCarreras.value?.get(_indiceActual.value ?: 0)
        if (carreraActual != null && _indiceImagenActual.value!! < carreraActual.imagenes.size - 1) {
            _indiceImagenActual.value = _indiceImagenActual.value!! + 1
        }
    }

    fun imagenAnterior() {
        if (_indiceImagenActual.value!! > 0) {
            _indiceImagenActual.value = _indiceImagenActual.value!! - 1
        }
    }
}
