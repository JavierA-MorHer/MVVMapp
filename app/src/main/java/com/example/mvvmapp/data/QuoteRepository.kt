package com.example.mvvmapp.data

import com.example.mvvmapp.data.database.dao.QuoteDao
import com.example.mvvmapp.data.database.entities.QuoteEntity
import com.example.mvvmapp.data.model.QuoteModel
import com.example.mvvmapp.data.model.QuoteProvider
import com.example.mvvmapp.data.network.QuoteService
import com.example.mvvmapp.domain.model.Quote
import com.example.mvvmapp.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api : QuoteService,
    private val quoteProvider: QuoteProvider,
    private val quoteDao: QuoteDao
) {



    //Corrutina
    suspend fun getAllQuotesFromApi():List<Quote>{
        //Se manda llamar el api
         val response:List<QuoteModel> = api.getQuotes()

        //Lleno la "BD" mediante la respuesta de esa api
        quoteProvider.quotes = response

        //Retorno la respuesta mapeandola
        return response.map{ it.toDomain() }
    }

    suspend fun getAllQuotesFromDB():List<Quote>{
        val response = quoteDao.getAllQuotes()
        return response.map{ it.toDomain() }
    }

    suspend fun insertQuotes(quotes:List<QuoteEntity>){
        quoteDao.insertAll(quotes)
    }

    suspend fun clearQuotes() {
        quoteDao.deleteAllQuotes()
    }
}