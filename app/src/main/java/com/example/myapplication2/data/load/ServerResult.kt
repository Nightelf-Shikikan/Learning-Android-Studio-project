package com.example.myapplication2.data.load


sealed class ServerResult {
    object Idle : ServerResult()
    object Loading : ServerResult()
    data class Success(val message: String) : ServerResult()
    data class Error(val errorMessage: String) : ServerResult()
}
