package com.example.kinopoisk.model.retrofit.api
import com.example.kinopoisk.model.retrofit.data.Film
import retrofit2.Response
import retrofit2.http.*

interface KinopoiskService{
    @GET("/api/v2.2/films/{id}")
    suspend fun getFilm(
        @Path("id") id: Long):Response<Film>
}