package com.example.mvvmapp.data.network

import com.example.mvvmapp.data.model.QuoteModel
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApiClient {

    //Se manda llamar el api para obtener todas las citas de la api
    @GET("/.json")
    suspend fun getAllQuotes():Response<List<QuoteModel>>
}