package com.example.mvvmapp.domain

import com.example.mvvmapp.data.QuoteRepository
import com.example.mvvmapp.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


internal class GetRandomQuoteUseCaseTest{

    // @Mock si accede a algo que no hemos preparado va a fallar
    @RelaxedMockK //Si no definimos una respuesta de la clase que estamos creando, nos la va a generar automaticamente
    private lateinit var quoteRepository: QuoteRepository

    lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getRandomQuoteUseCase = GetRandomQuoteUseCase(quoteRepository)
    }

    @Test
    fun `when database is empty return null`()= runBlocking{
        //Given
        coEvery { quoteRepository.getAllQuotesFromDB() } returns emptyList()


        //When
        val res = getRandomQuoteUseCase()


        //Then
        assert(res == null)
    }

    @Test
    fun `when database isnt null return a quote`() = runBlocking{
        //Given
        val quoteList = listOf(Quote("Holis","Javis"))
        coEvery { quoteRepository.getAllQuotesFromDB() } returns quoteList

        //When
        val res = getRandomQuoteUseCase()

        //Then
        assert(res == quoteList.first())

    }


}