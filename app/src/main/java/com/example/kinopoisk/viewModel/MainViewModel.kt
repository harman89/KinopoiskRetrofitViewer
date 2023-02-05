package com.example.kinopoisk.viewModel

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kinopoisk.R
import com.example.kinopoisk.model.db.UserClass
import com.example.kinopoisk.model.repository.User
import com.example.kinopoisk.model.repository.UserRepository

class MainViewModel(private val repository: UserRepository = User()) : ViewModel() {
    var user: LiveData<UserClass?> = MutableLiveData<UserClass?>()
    //var user : UserClass? = null
    fun insertUser(email: String, password: String) {
        repository.insertUser(UserClass(email,password,false))
    }

    fun getUser(email: String, password: String): LiveData<UserClass?> {
        user = repository.getUser(email,password)
        return user
    }

    fun nextFragment(fragment: Fragment, nextFragment: Fragment) {
        fragment.parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, nextFragment)
            .commit()
    }

    fun forgetLoggedUser() {
        repository.forgetLoggedUser()
    }

    fun loginLoggedUser(): LiveData<UserClass?> {
        user = repository.loginLoggedUser()
        return user
    }
}