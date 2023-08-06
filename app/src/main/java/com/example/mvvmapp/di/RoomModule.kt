package com.example.mvvmapp.di


import android.content.Context
import androidx.room.Room
import com.example.mvvmapp.data.database.QuoteDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val QUOTE_DATABASE_NAME = "quote_database"

    //Se crea la BD
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, QuoteDB::class.java, QUOTE_DATABASE_NAME).build()

    //Se obtiene el DAO
    @Singleton
    @Provides
    fun provideQuoteDao(db:QuoteDB) = db.getQuoteDao()
}