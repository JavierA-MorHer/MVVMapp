package com.example.mvvmapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmapp.data.model.QuoteModel
import com.example.mvvmapp.data.model.QuoteProvider
import com.example.mvvmapp.domain.GetQuotesUseCase
import com.example.mvvmapp.domain.GetRandomQuoteUseCase
import kotlinx.coroutines.launch

class QuoteViewModel: ViewModel() {

    //LiveData nos ayudara a ir actualizando la data de manera que en el View se estara suscrito a esa data y los cambios seran reactivos
    //Encapsulamos el modelo que queremos en el LiveData, es Mutable porque va a estar cambiando
    val quoteModel = MutableLiveData<QuoteModel>()

    val isLoading = MutableLiveData<Boolean>()

    var getQuotesUseCase = GetQuotesUseCase()

    var getRandomQuoteUseCase = GetRandomQuoteUseCase()

    //Aqui se hace la llamada al caso de uso para que nos regrese todas las quotes y las almacene en memoria
    fun onCreate() {
        //viewModelScope nos ayuda a crear una corrutina que se controla automaticamente
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getQuotesUseCase()
            if(!result.isNullOrEmpty()){
                quoteModel.postValue(result[0])
                isLoading.postValue(false)

            }
        }
    }

    fun randomQuote(){
        isLoading.postValue(true)
//        val currentQuote = QuoteProvider.random()
//        //Se manda el nuevo valor al LiveData que sera consumido en el MainActivity
//        quoteModel.postValue(currentQuote)

        var quote:QuoteModel? = getRandomQuoteUseCase()
        if(quote!=null){
            //Esto da error pero si funciona :)
            quoteModel.postValue(quote)
        }
        isLoading.postValue(false)

    }


}