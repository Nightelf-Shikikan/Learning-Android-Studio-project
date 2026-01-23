package com.example.myapplication2.di

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import com.example.myapplication2.data.api.ApiClient

@Module
object ApiModule {

    @Provides
    @Singleton
    @NewsApiClient
    fun provideClientNews(): ApiClient {
        return ApiClient("https://api.first.com/")
    }

    @Provides
    @Singleton
    @WeatherApiClient
    fun provideClientWeather(): ApiClient {
        return ApiClient("https://api.second.com/")
    }
}