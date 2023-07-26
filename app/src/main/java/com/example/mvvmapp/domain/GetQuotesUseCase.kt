package com.example.mvvmapp.domain

import com.example.mvvmapp.data.QuoteRepository
import com.example.mvvmapp.data.model.QuoteModel

class GetQuotesUseCase {

    private val repository = QuoteRepository()

    //Con esta funcion operator, si se realiza una instancia de la clase no se necesitaria poner GetQuotesUseCase().nombreFun
    // sino que solamente colocando GetQuotesUseCase() se llama a esa funcion
    suspend operator fun invoke():List<QuoteModel>?{
        return repository.getAllQuotes()
    }
}