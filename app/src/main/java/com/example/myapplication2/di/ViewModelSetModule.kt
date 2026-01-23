package com.example.myapplication2.di

import androidx.lifecycle.ViewModel
import com.example.myapplication2.viewmodel.ApiViewModel
import com.example.myapplication2.viewmodel.CatalogViewModel
import com.example.myapplication2.viewmodel.Test1ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

// di/ViewModelSetModule.kt
@Module
object ViewModelSetModule {

    @Provides
    @IntoSet
    fun provideCatalogViewModel(vm: CatalogViewModel): ViewModel = vm

    @Provides
    @IntoSet
    fun provideTest1ViewModel(vm: Test1ViewModel): ViewModel = vm

    @Provides
    @IntoSet
    fun provideApiViewModel(vm: ApiViewModel): ViewModel = vm
}