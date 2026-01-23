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
    fun provideNewsApiClient(): ApiClient {
        return ApiClient("https://api.first.com/")
    }

    @Provides
    @Singleton
    @WeatherApiClient
    fun provideWeatherApiClient(): ApiClient {
        return ApiClient("https://api.second.com/")
    }
}