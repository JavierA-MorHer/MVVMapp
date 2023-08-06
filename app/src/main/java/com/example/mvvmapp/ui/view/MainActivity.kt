package com.example.mvvmapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.mvvmapp.databinding.ActivityMainBinding
import com.example.mvvmapp.ui.viewModel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint

//Con AndroidEntryPoint preparamos la clase para que pueda ser inyectada
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quoteViewModel : QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*En Kotlin, ActivityMainBinding.inflate(layoutInflater) es una forma de utilizar
        View Binding para asociar las vistas (UI) de un layout XML con el código Kotlin de una
        actividad (o fragmento) de manera más segura y eficiente. View Binding reemplaza el uso de
        findViewById y proporciona acceso directo a las vistas del layout sin necesidad de realizar
        llamadas adicionales o conversiones de tipos.*/
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quoteViewModel.onCreate()

        //Se suscribe al quoteModel para observar cambios, cada cambio le pondra el quote y el autor a los TextView
        quoteViewModel.quoteModel.observe(this, Observer {currentQuote ->
            binding.tvQuote.text = currentQuote.quote
            binding.tvAuthor.text = currentQuote.author
        })

        //Se suscribe al quoteModel para observar cambios, cada cambio le pondra el quote y el autor a los TextView
        quoteViewModel.isLoading.observe(this, Observer {
            //Se cambia el valor del boolean desde el ViewModel y con el it se lo pasamos
            binding.progress.isVisible = it
        })



        //Cada que se haga click en la pantalla se llamara al metodo que genera una random quote que llenara el LiveData
        binding.viewContainer.setOnClickListener{
            quoteViewModel.randomQuote()
        }
    }
}


