package com.example.kinopoisk.model.db
import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [UserClass::class], version = 1)
abstract class dbClass : RoomDatabase() {
    abstract fun dao():Dao
}