package com.example.kinopoisk.model.retrofit.api
import com.example.kinopoisk.model.retrofit.data.Film
import com.example.kinopoisk.model.retrofit.data.TopFilms
import retrofit2.Response
import retrofit2.http.*

interface KinopoiskService{
    @GET("/api/v2.2/films/{id}")
    suspend fun getFilm(
        @Path("id") id: Long):Response<Film>
    @GET("/api/v2.2/films/top")
    suspend fun getTopFilms(
        @Query("page") page: Int,
        @Query("type") type: String
    ):Response<TopFilms>
}