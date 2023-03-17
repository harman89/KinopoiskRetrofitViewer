package com.example.kinopoisk.model.db
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

    @Database(entities = [UserClass::class, FilmCache::class], version = 2, exportSchema = true,autoMigrations = [AutoMigration(from = 1, to = 2)])
//@Database(entities = [UserClass::class], version = 1, exportSchema = true)
abstract class dbClass : RoomDatabase() {
    abstract fun dao():Dao
}