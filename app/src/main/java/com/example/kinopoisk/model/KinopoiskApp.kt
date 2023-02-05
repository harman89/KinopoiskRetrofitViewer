package com.example.kinopoisk.model

import android.app.Application
import androidx.room.Room
import com.example.kinopoisk.model.db.dbClass

class KinopoiskApp : Application() {
    companion object{
        lateinit var db : dbClass
        private lateinit var instance : KinopoiskApp
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        db = Room.databaseBuilder(instance,dbClass::class.java,"kinopoiskDb").build()
    }
}