package com.example.myprofile.ui.acteurs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myprofile.ui.api.Tmdbapi
import com.example.myprofile.ui.models.Actor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ActeurViewModel: ViewModel() {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    val api = retrofit.create(Tmdbapi::class.java)
    val acteurs = MutableStateFlow<List<Actor>>(listOf())

    init {
        getActors()
    }

    fun getActors() {
        viewModelScope.launch {
            acteurs.value = api.lastactorOfWeek("").results //permet d'accéder aux résultats qui sont une listes de films
        }
    }

    fun searchActors(texte: String) {
        viewModelScope.launch {
            acteurs.value = api.searchActor(texte, "").results
        }
    }
}