package com.example.mvvmapp.data.database.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mvvmapp.domain.model.Quote

//Esta va a ser una tabla de nuestra base de datos
@Entity(tableName = "quote_table")
data class QuoteEntity (
    //Primary key y en column info va la informacion de la columna
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id") val id:Int = 0,
    @ColumnInfo(name="quote") val quote:String,
    @ColumnInfo(name="author") val author:String
 )

fun Quote.toDatabase()= QuoteEntity( quote = quote, author = author)