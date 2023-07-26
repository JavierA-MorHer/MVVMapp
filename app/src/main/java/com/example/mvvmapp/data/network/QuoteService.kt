package com.example.mvvmapp.data.network

import com.example.mvvmapp.core.RetrofitHelper
import com.example.mvvmapp.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class QuoteService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getQuotes(): List<QuoteModel>{
        //Se mete en una corrutina
        return withContext(Dispatchers.IO){
            //Obtenemos la respuesta de la api
            val response:Response<List<QuoteModel>> = retrofit.create(QuoteApiClient::class.java).getAllQuotes()
            //Si el response.body es nulo entonces devuelve una lista vacia
            response.body() ?: emptyList()
        }

    }

}