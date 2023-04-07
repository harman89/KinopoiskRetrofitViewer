package com.example.kinopoisk.viewModel

import android.graphics.Movie
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk.R
import com.example.kinopoisk.model.db.FilmCache
import com.example.kinopoisk.model.db.UserClass
import com.example.kinopoisk.model.repository.User
import com.example.kinopoisk.model.repository.UserRepository
import com.example.kinopoisk.model.retrofit.data.Film
import com.example.kinopoisk.model.retrofit.data.TopFilms
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: UserRepository = User()) : ViewModel() {
    var user: LiveData<UserClass?> = MutableLiveData<UserClass?>()
    val film :MutableLiveData<Response<Film>> = MutableLiveData()
    val listFilms : MutableLiveData<Response<TopFilms>> = MutableLiveData()
    var chosenFilmId : MutableLiveData<Long> = MutableLiveData()
    var listOfflineFilms : LiveData<FilmCache?> = MutableLiveData<FilmCache?>()
    fun insertUser(email: String, password: String) {
        repository.insertUser(UserClass(email,password,false))
    }

    fun getUser(email: String, password: String): LiveData<UserClass?> {
        user = repository.getUser(email,password)
        return user
    }

    fun getOfflineFilms() : LiveData<FilmCache?>{
        listOfflineFilms = repository.getCacheFilm()
        return listOfflineFilms
    }

    fun nextFragment(fragment: Fragment, nextFragment: Fragment) {
        fragment.parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, nextFragment)
            .commit()
    }
    fun updateUser(user: UserClass) {
        repository.updateUser(user)
    }
    fun forgetLoggedUser() {
        repository.forgetLoggedUser()
    }

    fun loginLoggedUser(): LiveData<UserClass?> {
        user = repository.loginLoggedUser()
        return user
    }
    fun getFilm(id:Long){
        viewModelScope.launch {
            film.value = repository.getFilm(id)
        }
    }
    fun getTopFilms(page: Int, type : String){
        viewModelScope.launch {
            listFilms.value = repository.getTopFilms(page,type)
        }
    }
    fun setChosenFilm( id : Long) {
        viewModelScope.launch {
            chosenFilmId.value = id
        }
    }
    fun insertCacheFilm(JSON : String){
        repository.insertCacheFilm(FilmCache(JSON))
    }
    fun updateCacheFilm(id : Long,JSON : String){
        repository.updateCacheFilm(FilmCache(id, JSON))
    }
    fun isOnline() : Boolean{
        return repository.isOnline()
    }

}