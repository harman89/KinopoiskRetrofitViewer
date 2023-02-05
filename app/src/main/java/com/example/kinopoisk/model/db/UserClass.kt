package com.example.kinopoisk.model.db
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "User")
public data class UserClass (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Long ,
    @ColumnInfo(name = "email")
    var email : String,
    @ColumnInfo(name = "password")
    var password : String,
    @ColumnInfo(name = "isLogged")
    var  isLogged : Boolean)
{
    constructor(email: String,password: String,isLogged: Boolean) : this(0,email,password,isLogged)
}