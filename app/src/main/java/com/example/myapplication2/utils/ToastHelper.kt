package com.example.myapplication2.utils

// utils/ToastHelper.kt


import android.content.Context
import android.widget.Toast
import javax.inject.Inject

class ToastHelper @Inject constructor(
    private val context: Context
) {
    fun show(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}