package com.example.myapplication2.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NewsApiClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class WeatherApiClient

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext