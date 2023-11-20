package com.example.myprofile.ui.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myprofile.ui.api.Tmdbapi
import com.example.myprofile.ui.models.DetailedSerie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class SerieSelectedViewModel: ViewModel() {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val api = retrofit.create(Tmdbapi::class.java)
    val serie = MutableStateFlow<DetailedSerie>(DetailedSerie())

    fun getSerieSelected(id: String) {
        viewModelScope.launch {
            serie.value = api.overviewOfSerie(id, "") //permet d'accéder aux résultats qui sont une listes de films
        }
    }

}