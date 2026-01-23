package com.example.myapplication2.viewmodel

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MultiViewModelFactory @Inject constructor(
    private val viewModels: Set<ViewModel>
) {
    fun getAll(): Set<ViewModel> = viewModels
}