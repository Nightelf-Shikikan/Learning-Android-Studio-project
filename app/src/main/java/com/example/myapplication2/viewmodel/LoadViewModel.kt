package com.example.myapplication2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

import com.example.myapplication2.data.load.ServerResult

class LoadViewModel : ViewModel() {
    private val _serverResult = MutableStateFlow<ServerResult>(ServerResult.Idle)
    val serverResult: StateFlow<ServerResult> = _serverResult

    fun sendRequest() {
        viewModelScope.launch {
            _serverResult.value = ServerResult.Loading
            delay(2000)
            val isError = Random.nextBoolean()
            _serverResult.value = if (isError)
                ServerResult.Error("Server error occurred (500)")
            else
                ServerResult.Success("Data loaded successfully")
        }
    }
}
