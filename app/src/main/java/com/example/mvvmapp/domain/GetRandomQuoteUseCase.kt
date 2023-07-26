package com.example.mvvmapp.domain

import com.example.mvvmapp.data.QuoteRepository
import com.example.mvvmapp.data.model.QuoteModel
import com.example.mvvmapp.data.model.QuoteProvider

class GetRandomQuoteUseCase {

    operator fun invoke():QuoteModel?{
        //Obtengo las quotes
        val quotes = QuoteProvider.quotes
        //Si no es vacio o null genero un numero random entre 0 y el size de las quotes
        if(!quotes.isNullOrEmpty()){
            val randomNumber = (0..quotes.size-1).random()
            //Retorno esa quote en especifico
            return quotes[randomNumber]
        }
        return null
    }
}