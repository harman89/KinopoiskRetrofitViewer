package com.example.kinopoisk.model.db
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "FilmCache")
public data class FilmCache (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Long ,
    @ColumnInfo(name = "JSON")
    var JSON : String
    )
{
    constructor(JSON: String) : this(0,JSON)
}