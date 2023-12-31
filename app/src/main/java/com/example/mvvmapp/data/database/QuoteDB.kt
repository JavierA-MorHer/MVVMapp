package com.example.mvvmapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvmapp.data.database.dao.QuoteDao
import com.example.mvvmapp.data.database.entities.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteDB: RoomDatabase() {

    //Por cada DAO que tengamos vamos a tener que crear una funcion abstracta
    abstract fun getQuoteDao():QuoteDao

}