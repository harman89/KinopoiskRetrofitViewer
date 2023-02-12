package com.example.kinopoisk.model.repository

import androidx.lifecycle.LiveData
import com.example.kinopoisk.model.db.UserClass
import com.example.kinopoisk.model.retrofit.data.Film
import retrofit2.Response

interface UserRepository {
    fun insertUser(user : UserClass)
    fun getUser(email : String, password : String) : LiveData<UserClass?>
    fun loginLoggedUser(): LiveData<UserClass?>
    fun forgetLoggedUser()
    suspend fun getFilm(id:Long) : Response<Film>
}