package com.example.kinopoisk.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.kinopoisk.model.KinopoiskApp
import com.example.kinopoisk.model.db.FilmCache
import com.example.kinopoisk.model.db.UserClass
import com.example.kinopoisk.model.db.dbClass
import com.example.kinopoisk.model.retrofit.api.KinopoiskApi
import com.example.kinopoisk.model.retrofit.data.Film
import com.example.kinopoisk.model.retrofit.data.TopFilms
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class User : UserRepository {
    val db : dbClass = KinopoiskApp.db
    override fun insertUser(user: UserClass) {
        CoroutineScope(Dispatchers.Default).launch {
        db.dao().insertUser(user)
        }
    }

    override fun getUser(email : String, password : String) : LiveData<UserClass?> {
        return db.dao().getUser(email,password)
    }

    override fun loginLoggedUser(): LiveData<UserClass?> {
        return db.dao().getIsLogged()
    }

    //NOT USED
    override fun forgetLoggedUser() {
        var user : LiveData<UserClass?> = db.dao().getIsLogged()
        user.value?.isLogged = false
        user.value?.let { db.dao().updateUser(it) }
    }

    override fun updateUser(user : UserClass) {
        CoroutineScope(Dispatchers.Default).launch{
            db.dao().updateUser(user)
        }
    }
    override suspend fun getFilm(Id: Long) : Response<Film> {
        return KinopoiskApi.api.getFilm(Id)
    }
    override suspend fun getTopFilms(page : Int, type : String) : Response<TopFilms> {
        return KinopoiskApi.api.getTopFilms(page, type)
    }

    override fun insertCacheFilm(filmCache: FilmCache) {
        CoroutineScope(Dispatchers.Default).launch{
            db.dao().insertCacheFilms(filmCache)
        }
    }

    override fun updateCacheFilm(filmCache: FilmCache) {
        CoroutineScope(Dispatchers.Main).launch{
            db.dao().updateCacheFilms(filmCache)
        }
    }

    override fun getCacheFilm(): LiveData<FilmCache?> {
        Log.d("SQLTRACE","DB" + db.dao().getFilms())
        return db.dao().getFilms()
    }

    override fun parseCacheFilm() : TopFilms? {
        return Gson().fromJson(getCacheFilm().value?.JSON, TopFilms::class.java)
    }
    override fun isOnline(): Boolean {
        val runtime = Runtime.getRuntime()
        try {
            val ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8")
            val exitValue = ipProcess.waitFor()
            return exitValue == 0
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return false
    }
}