package com.example.mvvmapp.domain

import com.example.mvvmapp.data.QuoteRepository
import com.example.mvvmapp.data.database.entities.toDatabase
import com.example.mvvmapp.data.model.QuoteModel
import com.example.mvvmapp.domain.model.Quote
import javax.inject.Inject

//Con @Inject constructor() la clase se pone lista para ser inyectada

class GetQuotesUseCase @Inject constructor(
    private val repository : QuoteRepository
) {

    //Con esta funcion operator, si se realiza una instancia de la clase no se necesitaria poner GetQuotesUseCase().nombreFun
    // sino que solamente colocando GetQuotesUseCase() se llama a esa funcion
    /*suspend operator fun invoke():List<QuoteModel>?{
        return repository.getAllQuotes()
    }*/

    suspend operator fun invoke():List<Quote>{
        val quotes = repository.getAllQuotesFromApi()

      return  if(quotes.isNotEmpty()){
          repository.clearQuotes()
            //Se mapea la informacion para que sea del tipo requerido
            repository.insertQuotes(quotes.map{ it.toDatabase() })
            quotes
        }else{
            return repository.getAllQuotesFromDB()
        }


    }
}