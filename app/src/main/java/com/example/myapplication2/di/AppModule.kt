package com.example.myapplication2.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideAppContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun providePlainContext(application: Application): Context = application.applicationContext
}