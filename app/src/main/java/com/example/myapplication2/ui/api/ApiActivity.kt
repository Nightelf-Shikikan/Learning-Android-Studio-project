package com.example.myapplication2.ui.api



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication2.MyApp
import com.example.myapplication2.R
import com.example.myapplication2.data.api.ApiClient
import com.example.myapplication2.di.NewsApiClient
import com.example.myapplication2.di.WeatherApiClient
import com.example.myapplication2.utils.ToastHelper
import com.example.myapplication2.viewmodel.ApiViewModel
import com.example.myapplication2.viewmodel.ApiViewModelFactory
import javax.inject.Inject

class ApiActivity : AppCompatActivity() {


    @Inject
    lateinit var toastHelper: ToastHelper // from AppComponent

    private lateinit var viewModel: ApiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)

        class ApiActivity @Inject constructor(
            @NewsApiClient private val clientOne: ApiClient,
            @WeatherApiClient private val clientTwo: ApiClient
        ) {
            fun testClients() {
                println(clientOne.baseUrl) // prints https://api.first.com/
                println(clientTwo.baseUrl) // prints https://api.second.com/
            }
        }
        // Inject AppComponent dependencies
        (application as MyApp).appComponent.inject(this)

        // Create factory with runtime value
        val factory = ApiViewModelFactory(toastHelper)

        // Create ViewModel
        viewModel = ViewModelProvider(this, factory)[ApiViewModel::class.java]

        // Use it
        viewModel.showApiKey("MY_SECRET_KEY")


    }
}