package com.example.myapplication2.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication2.utils.ToastHelper
import javax.inject.Inject

class ApiViewModel @Inject constructor(
    private val toastHelper: ToastHelper,

) : ViewModel() {


    fun showApiKey(apiKey: String) {
        toastHelper.show("API Key:$apiKey" )
    }
}