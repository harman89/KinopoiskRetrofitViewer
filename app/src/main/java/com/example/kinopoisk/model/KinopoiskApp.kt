package com.example.kinopoisk.model

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kinopoisk.model.db.dbClass
import java.util.concurrent.Executors

class KinopoiskApp : Application() {
    companion object{
        lateinit var db : dbClass
        private lateinit var instance : KinopoiskApp
        val API_KEY = "1d4209fb-a97c-4ac8-a3e7-e0a54f3ca88e"
        val API_URL = "https://kinopoiskapiunofficial.tech"
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        var dbBuilder = Room.databaseBuilder(instance,dbClass::class.java,"kinopoiskDb")
        val queryCallback = object : RoomDatabase.QueryCallback{
            override fun onQuery(sqlQuery: String, bindArgs: List<Any?>) : Unit {
                println("SQL Query: $sqlQuery SQL Args: $bindArgs")
            }
        }
        dbBuilder.setQueryCallback(queryCallback, Executors.newSingleThreadExecutor())
        db = dbBuilder.build()
    }
}