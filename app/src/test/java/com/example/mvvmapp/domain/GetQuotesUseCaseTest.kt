package com.example.mvvmapp.domain

import com.example.mvvmapp.data.QuoteRepository
import com.example.mvvmapp.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


/*
    Clase creada para el test de la clase GetQuotesUseCase
    Se crea pulsando Navigate/Test y creado el archivo correspondiente
 */
internal class GetQuotesUseCaseTest{

    // @Mock si accede a algo que no hemos preparado va a fallar
    @RelaxedMockK //Si no definimos una respuesta de la clase que estamos creando, nos la va a generar automaticamente
    private lateinit var quoteRepository: QuoteRepository

    lateinit var getQuotesUseCase: GetQuotesUseCase


    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getQuotesUseCase = GetQuotesUseCase(quoteRepository)
    }

    @Test
    fun `when API doesnt Return Anthing Then Get Values From Database`()= runBlocking{
        //Given
        //Se coloca coEvery si la funcion que testeamos es una corrutina, sino seria solamente every{}
        //Cada que se mande llamar el metodo getAllQuotesFromApi() se va a enviar una lista vacia
        coEvery { quoteRepository.getAllQuotesFromApi() } returns emptyList()


        //When
        getQuotesUseCase()


        //Then
        //Verifica que se esta llamando esa funcion y que solamente se llamo una vez
        coVerify(exactly = 1) { quoteRepository.getAllQuotesFromDB() }
    }

    @Test
    fun `when API Return Something Then Get Values From API`()= runBlocking {
        //Given
        val myList = listOf(Quote("HOLA","javis"))
        coEvery { quoteRepository.getAllQuotesFromApi() } returns myList

        //When
        val res = getQuotesUseCase()

        //Then
        coVerify(exactly = 1) {quoteRepository.clearQuotes() }
        coVerify(exactly = 1) {quoteRepository.insertQuotes(any()) }
        coVerify(exactly = 0) { quoteRepository.getAllQuotesFromDB() }
        assert(myList == res)



    }
}