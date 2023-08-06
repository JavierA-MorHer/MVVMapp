package com.example.mvvmapp.data.model

import javax.inject.Inject
import javax.inject.Singleton

//Se coloca Singleton para que no se creen multiples instancias de este arreglo de quotes
@Singleton
class QuoteProvider @Inject constructor(){
        var quotes :List<QuoteModel> = emptyList()
}