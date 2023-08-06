package com.example.mvvmapp.di

import com.example.mvvmapp.data.network.QuoteApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//Este modulo nos va a proveeer dependencias que no sean tan faciles de proveer
// como por ejemplo librerias o dependencias de clases que contienen interfaces

//Module porque los modulos son los que proveen dependencias
@Module
@InstallIn(SingletonComponent::class)//Entre parentesis va el alcance de las instancias que se van a crear de las dependencias que mandemos llamar
object NetworkModule {

    @Singleton //Mantiene una unica instancia de Retrofit si esta creado o no
    @Provides
    //se crea una funcion con lo que voy a proveer provideNOMBRE
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://drawsomething-59328-default-rtdb.europe-west1.firebasedatabase.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun provideQuoteApiClient(retrofit: Retrofit):QuoteApiClient{
        return retrofit.create(QuoteApiClient::class.java)
    }
}