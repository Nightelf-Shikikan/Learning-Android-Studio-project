package com.example.myapplication2.data.api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApiClient {

    private val BASE_URL = "https://api.example.com/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
           .baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}