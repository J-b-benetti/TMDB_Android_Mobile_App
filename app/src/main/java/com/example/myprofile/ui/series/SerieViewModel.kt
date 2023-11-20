package com.example.myprofile.ui.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myprofile.ui.api.Tmdbapi
import com.example.myprofile.ui.models.Serie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class SerieViewModel: ViewModel() {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val api = retrofit.create(Tmdbapi::class.java)
    val series = MutableStateFlow<List<Serie>>(listOf())

    init {
        getSeries()
    }

    fun getSeries() {
        viewModelScope.launch {
            series.value = api.lastserieOfWeek("").results //permet d'accéder aux résultats qui sont une listes de séries
        }
    }

    fun searchSerie(texte: String) {
        viewModelScope.launch {
            series.value = api.searchSerie(texte, "").results
        }
    }
}