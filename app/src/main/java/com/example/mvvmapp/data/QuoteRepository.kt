package com.example.mvvmapp.data

import com.example.mvvmapp.data.model.QuoteModel
import com.example.mvvmapp.data.model.QuoteProvider
import com.example.mvvmapp.data.network.QuoteService

class QuoteRepository {

    private val api = QuoteService()

    //Corrutina
    suspend fun getAllQuotes():List<QuoteModel>{
        //Se manda llamar el api
         val response:List<QuoteModel> = api.getQuotes()

        //Lleno la "BD" mediante la respuesta de esa api
        QuoteProvider.quotes = response

        //Retorno la respuesta
        return response
    }
}