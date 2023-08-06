package com.example.mvvmapp.data.network

import com.example.mvvmapp.core.RetrofitHelper
import com.example.mvvmapp.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class QuoteService @Inject constructor(
    private val api:QuoteApiClient
){

    suspend fun getQuotes(): List<QuoteModel>{
        //Se mete en una corrutina
        return withContext(Dispatchers.IO){
            //Obtenemos la respuesta de la api
            val response:Response<List<QuoteModel>> = api.getAllQuotes()
            //Si el response.body es nulo entonces devuelve una lista vacia
            response.body() ?: emptyList()
        }

    }

}