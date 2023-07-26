package com.example.mvvmapp.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    //Configuracion de retroFit
    /*
        Retrofit es una biblioteca de cliente de red muy popular para Android
        (y también puede ser utilizada en proyectos Kotlin fuera de Android) que simplifica la tarea de
        realizar solicitudes HTTP a servicios web y consumir API REST. Fue desarrollado por Square Inc. y está escrito en Java,
        pero puede ser fácilmente utilizado en proyectos Kotlin debido a la interoperabilidad entre ambos lenguajes.
    */
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
             .baseUrl("https://drawsomething-59328-default-rtdb.europe-west1.firebasedatabase.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}