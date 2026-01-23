package com.example.myapplication2.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication2.viewmodel.ApiViewModel
import com.example.myapplication2.viewmodel.CatalogViewModel
import com.example.myapplication2.viewmodel.Test1ViewModel
import dagger.Binds
import dagger.multibindings.IntoMap
import dagger.Module

// di/ViewModelBindingModule.kt
@Module
abstract class ViewModelBindingModule {

    @Binds
    abstract fun bindViewModelFactory(
        factory: DaggerViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CatalogViewModel::class)
    abstract fun bindCatalogViewModel(
        viewModel: CatalogViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(Test1ViewModel::class)
    abstract fun bindTest1ViewModel(
        viewModel: Test1ViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ApiViewModel::class)
    abstract fun bindApiViewModel(
        viewModel: ApiViewModel
    ): ViewModel
}