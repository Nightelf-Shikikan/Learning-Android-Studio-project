package com.example.myapplication2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication2.utils.ToastHelper

class ApiViewModelFactory(
    private val toastHelper: ToastHelper,

) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApiViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ApiViewModel(toastHelper) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}