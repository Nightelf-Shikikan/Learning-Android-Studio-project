package com.example.myapplication2.model

sealed class ResultState {
    object Idle : ResultState()
    object Loading : ResultState()
    data class Success(val data: String) : ResultState()
    data class Error(val message: String) : ResultState()
}