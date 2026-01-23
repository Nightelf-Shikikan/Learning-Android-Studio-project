package com.example.myapplication2.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.myapplication2.utils.ToastHelper
import javax.inject.Inject

class Test1ViewModel @Inject constructor(
    private val toastHelper: ToastHelper
) : ViewModel() {

    fun sayHello() {
        toastHelper.show("Hello from ViewModel!")
    }
}