package com.example.kinopoisk.model.retrofit.data

data class TopFilms(
    val films: List<FilmLite>,
    val pagesCount: Int
)