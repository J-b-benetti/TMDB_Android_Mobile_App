package com.example.myprofile.ui.films

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myprofile.ui.api.Tmdbapi
import com.example.myprofile.ui.models.Film
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class HomeViewModel: ViewModel() {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val api = retrofit.create(Tmdbapi::class.java)
    val movies = MutableStateFlow<List<Film>>(listOf())

    init {
        getMovies()
    }

    fun getMovies() {
        viewModelScope.launch {
            movies.value = api.lastmoviesOfWeek("").results //permet d'accéder aux résultats qui sont une listes de films
        }
    }

    fun searchMovie(texte: String) {
        viewModelScope.launch {
            movies.value = api.searchMovie(texte, "").results
        }
    }

}