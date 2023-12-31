package com.example.mvvmapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmapp.data.model.QuoteModel
import com.example.mvvmapp.data.model.QuoteProvider
import com.example.mvvmapp.domain.GetQuotesUseCase
import com.example.mvvmapp.domain.GetRandomQuoteUseCase
import com.example.mvvmapp.domain.model.Quote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//Se preparo el ViewModel para que se le puedan inyectar dependencias
@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuotesUseCase:GetQuotesUseCase,
    private val getRandomQuoteUseCase:GetRandomQuoteUseCase
): ViewModel() {

    //LiveData nos ayudara a ir actualizando la data de manera que en el View se estara suscrito a esa data y los cambios seran reactivos
    //Encapsulamos el modelo que queremos en el LiveData, es Mutable porque va a estar cambiando
    val quoteModel = MutableLiveData<Quote>()

    val isLoading = MutableLiveData<Boolean>()

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
        viewModelScope.launch {
            isLoading.postValue(true)
    //        val currentQuote = QuoteProvider.random()
    //        //Se manda el nuevo valor al LiveData que sera consumido en el MainActivity
    //        quoteModel.postValue(currentQuote)

                var quote= getRandomQuoteUseCase()
                if(quote!=null){
                    //Esto da error pero si funciona :)
                    quoteModel.postValue(quote)
                }
                isLoading.postValue(false)
        }


    }


}