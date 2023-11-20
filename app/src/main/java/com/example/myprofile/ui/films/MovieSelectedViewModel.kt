package com.example.myprofile.ui.films

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myprofile.ui.api.Tmdbapi
import com.example.myprofile.ui.models.DetailedMovie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieSelectedViewModel: ViewModel() {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val api = retrofit.create(Tmdbapi::class.java)
    val movie = MutableStateFlow<DetailedMovie>(DetailedMovie())


    fun getMovieSelected(id: String) {
        viewModelScope.launch {
            movie.value = api.overviewOfMovie(id, "") //permet d'accéder aux résultats qui sont une listes de films
        }
    }
}