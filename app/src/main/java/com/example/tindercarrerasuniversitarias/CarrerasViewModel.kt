package com.example.tindercarrerasuniversitarias

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CarrerasViewModel : ViewModel() {
    private val _carrerasList = MutableLiveData<MutableList<CarrerasUniversitarias>>()
    val carrerasList: LiveData<MutableList<CarrerasUniversitarias>> get() = _carrerasList

    private val _currentIndex = MutableLiveData<Int>()
    val currentIndex: LiveData<Int> get() = _currentIndex

    private val _currentImageIndex = MutableLiveData<Int>()
    val currentImageIndex: LiveData<Int> get() = _currentImageIndex

    // Lista para guardar las carreras "me gusta"
    private val _likedCarreras = MutableLiveData<MutableList<CarrerasUniversitarias>>(mutableListOf())
    val likedCarreras: LiveData<MutableList<CarrerasUniversitarias>> get() = _likedCarreras


    init {
        _carrerasList.value = mutableListOf(
            CarrerasUniversitarias("Administracion", mutableListOf(R.drawable.administracion1, R.drawable.administracion2, R.drawable.administracion3)),
            CarrerasUniversitarias("Ing.Comercial", mutableListOf(R.drawable.comercial1, R.drawable.comercial2, R.drawable.comercial3)),
            CarrerasUniversitarias("Comunicaion Social", mutableListOf(R.drawable.comunicacion1, R.drawable.comunicacion2, R.drawable.comunicacion3)),
            CarrerasUniversitarias("Contaduria", mutableListOf(R.drawable.contaduria1, R.drawable.contaduria2, R.drawable.contaduria3)),
            CarrerasUniversitarias("Derecho", mutableListOf(R.drawable.derecho1, R.drawable.derecho2, R.drawable.derecho3)),
            CarrerasUniversitarias("Ing.Financiera", mutableListOf(R.drawable.financiera1, R.drawable.financiera2, R.drawable.financiera3)),
            CarrerasUniversitarias("Fisioterapia", mutableListOf(R.drawable.fisio1, R.drawable.fisio2, R.drawable.fisio3)),
            CarrerasUniversitarias("marketing", mutableListOf(R.drawable.marketing1, R.drawable.marketing2, R.drawable.marketing3)),
            CarrerasUniversitarias("nutricion", mutableListOf(R.drawable.nutricion1, R.drawable.nutricion2, R.drawable.nutricion3)),
            CarrerasUniversitarias("psicologia", mutableListOf(R.drawable.psicologia1, R.drawable.psicologia2, R.drawable.psicologia3)),
            CarrerasUniversitarias("psicopedagogia", mutableListOf(R.drawable.psicopedagogia1, R.drawable.psicopedagogia2, R.drawable.psicopedagogia3)),
            CarrerasUniversitarias("redes", mutableListOf(R.drawable.redes1, R.drawable.redes2, R.drawable.redes3)),
            CarrerasUniversitarias("relaciones internacionales", mutableListOf(R.drawable.relacionesinternacionales1, R.drawable.relacionesinternacionales2, R.drawable.relacionesinternacionales3)),
            CarrerasUniversitarias("relaciones publicas", mutableListOf(R.drawable.relacionespublicas1, R.drawable.relacionespublicas2, R.drawable.relacionespublicas3)),
            CarrerasUniversitarias("Sistemas", mutableListOf(R.drawable.sistemas1, R.drawable.sistemas2, R.drawable.sistemas3)),
            CarrerasUniversitarias("turismo", mutableListOf(R.drawable.turismo1, R.drawable.turismo2, R.drawable.turismo3))
        )

        // Inicializo el índice actual y el índice de imagen actual
        _currentIndex.value = 0
        _currentImageIndex.value = 0

        // Inicializo la lista de mis me gusta
        _likedCarreras.value = mutableListOf()
    }


    fun likeCurrentCarrera() {
        val currentCarrera = _carrerasList.value?.get(_currentIndex.value ?: 0)
        currentCarrera?.let {
            val updatedList = _likedCarreras.value?.toMutableList() ?: mutableListOf()
            if (!updatedList.contains(it)) {
                updatedList.add(it)
                _likedCarreras.value = updatedList
            }
        }
        println(likedCarreras.value)
    }



    fun nextCarrera() {
        val currentIndexValue = _currentIndex.value ?: 0
        if (currentIndexValue < _carrerasList.value!!.size - 1) {
            _currentIndex.value = currentIndexValue + 1
            _currentImageIndex.value = 0
        }
    }

    fun nextImage() {
        val currentCarrera = _carrerasList.value?.get(_currentIndex.value ?: 0)
        if (currentCarrera != null && _currentImageIndex.value!! < currentCarrera.imagenes.size - 1) {
            _currentImageIndex.value = _currentImageIndex.value!! + 1
        }
    }

    fun previousImage() {
        if (_currentImageIndex.value!! > 0) {
            _currentImageIndex.value = _currentImageIndex.value!! - 1
        }
    }
}

