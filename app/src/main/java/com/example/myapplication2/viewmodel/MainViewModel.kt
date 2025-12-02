package com.example.myapplication2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.data.Repository
import com.example.myapplication2.model.ResultState

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MainViewModel
    : ViewModel() {
    private val repository = Repository()

    // Backing mutable state flow (private)
    private val _uiState = MutableStateFlow<ResultState>(ResultState.Idle)

    // Public read-only StateFlow
    val uiState: StateFlow<ResultState> = _uiState

    // Public function to start loading
    fun loadData() {
        // avoid launching multiple times if already loading
        if (_uiState.value is ResultState.Loading) return

        viewModelScope.launch {
            _uiState.value = ResultState.Loading
            try {
                val result = repository.fetchData()
                _uiState.value = ResultState.Success(result)
            } catch (e: Exception) {
                _uiState.value = ResultState.Error(e.message ?: "Unknown error")
            }
        }
    }

    // Optional: function to reset to idle
    fun reset() {
        _uiState.value = ResultState.Idle
    }
}