package com.example.kinopoisk.model.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {
    @Query("SELECT * FROM User where email = :email AND password = :password")
    fun getUser(email : String, password : String): LiveData<UserClass?>
    @Query("SELECT * FROM User where isLogged = TRUE")
    fun getIsLogged() : LiveData<UserClass?>
    @Update
    fun updateUser(user :UserClass)
    @Insert
    fun insertUser(user : UserClass)
    @Delete
    fun deleteUser(user: UserClass)
    @Insert
    fun insertCacheFilms(films : FilmCache)
    @Update
    fun updateCacheFilms(films : FilmCache)
    @Query("SELECT * FROM FilmCache where id = (select max(id) from FilmCache)")
    fun getFilms() : LiveData<FilmCache?>
}