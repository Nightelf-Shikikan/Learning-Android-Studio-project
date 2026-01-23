package com.example.myapplication2.ui.api


import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication2.MyApp
import com.example.myapplication2.R
import com.example.myapplication2.data.api.ApiClient
import com.example.myapplication2.di.NewsApiClient
import com.example.myapplication2.di.WeatherApiClient
import com.example.myapplication2.ui.catalog.CatalogFragment
import com.example.myapplication2.utils.ToastHelper
import com.example.myapplication2.viewmodel.ApiViewModel
import com.example.myapplication2.viewmodel.ApiViewModelFactory
import javax.inject.Inject

class ApiActivity : AppCompatActivity() {


    @Inject
    lateinit var toastHelper: ToastHelper // from AppComponent

    @Inject
    @NewsApiClient
    lateinit var clientOne: ApiClient

    @Inject
    @WeatherApiClient
    lateinit var clientTwo: ApiClient

    private lateinit var viewModel: ApiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)

        // Inject AppComponent dependencies
        (application as MyApp).appComponent.inject(this)

        // Create factory with runtime value
        val factory = ApiViewModelFactory(toastHelper)

        // Create ViewModel
        viewModel = ViewModelProvider(this, factory)[ApiViewModel::class.java]

        // Use it
        viewModel.showApiKey("MY_SECRET_KEY")

        val fetchButton = findViewById<Button>(R.id.TestApi)
        fetchButton.setOnClickListener {

            testClients()

        }


    }

    // Move the function here
    fun testClients() {
        println(clientOne.baseUrl) // prints https://api.first.com/
        println(clientTwo.baseUrl) // prints https://api.second.com/
        toastHelper.show("ClientOne: ${clientOne.baseUrl}, ClientTwo: ${clientTwo.baseUrl}")
    }

}