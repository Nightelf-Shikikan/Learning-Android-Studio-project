package com.example.myapplication2.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication2.utils.ToastHelper
import javax.inject.Inject

class StatusViewModel @Inject constructor(
    private val toastHelper: ToastHelper
) : ViewModel() {

    fun sayHello() {
        toastHelper.show("Status Screen")
    }
}