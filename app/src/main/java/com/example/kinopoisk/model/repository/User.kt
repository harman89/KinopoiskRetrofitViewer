package com.example.kinopoisk.model.repository

import androidx.lifecycle.LiveData
import com.example.kinopoisk.model.KinopoiskApp
import com.example.kinopoisk.model.db.UserClass
import com.example.kinopoisk.model.db.dbClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

    override fun forgetLoggedUser() {
        var user : LiveData<UserClass?> = db.dao().getIsLogged()
        user.value?.isLogged = false
        user.value?.let { db.dao().logOutUser(it) }
    }
}