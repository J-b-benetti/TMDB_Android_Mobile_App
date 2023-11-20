package com.example.myprofile.ui.api

import com.example.myprofile.ui.models.Actor
import com.example.myprofile.ui.models.Actors
import com.example.myprofile.ui.models.DetailedMovie
import com.example.myprofile.ui.models.DetailedSerie
import com.example.myprofile.ui.models.Film
import com.example.myprofile.ui.models.Films
import com.example.myprofile.ui.models.Serie
import com.example.myprofile.ui.models.Series
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Tmdbapi {
    @GET("trending/movie/week")
    suspend fun lastmoviesOfWeek(@Query("api_key") api_key: String): Films

    @GET("trending/tv/week")
    suspend fun lastserieOfWeek(@Query("api_key") api_key: String): Series

    @GET("trending/person/week")
    suspend fun lastactorOfWeek(@Query("api_key") api_key: String): Actors

    @GET("movie/{id}")
    suspend fun overviewOfMovie(@Path("id") id: String, @Query("api_key") api_key: String): DetailedMovie

    @GET("search/movie")
    suspend fun searchMovie(@Query("query") keyWord: String, @Query("api_key") api_key: String) : Films

    @GET("search/tv")
    suspend fun searchSerie(@Query("query") keyWord: String, @Query("api_key") api_key: String) : Series

    @GET("search/person")
    suspend fun searchActor(@Query("query") keyWord: String, @Query("api_key") api_key: String) : Actors

    @GET("tv/{id}")
    suspend fun overviewOfSerie(@Path("id") id: String, @Query("api_key") api_key: String): DetailedSerie
}