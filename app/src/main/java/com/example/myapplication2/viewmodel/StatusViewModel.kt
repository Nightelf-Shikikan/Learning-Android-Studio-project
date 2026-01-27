package com.example.myapplication2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication2.ui.status.StatusUiState
import com.example.myapplication2.ui.status.SystemStatusProvider
import com.example.myapplication2.utils.ToastHelper
import javax.inject.Inject

class StatusViewModel @Inject constructor(
    private val toastHelper: ToastHelper,
    val statusProvider: SystemStatusProvider
) : ViewModel() {

    private val _uiState = MutableLiveData(StatusUiState())
    val uiState: LiveData<StatusUiState> = _uiState

    fun refresh() {
        _uiState.value = StatusUiState(
            batteryPercent = statusProvider.getBatteryPercent(),
            isWifiConnected = statusProvider.isWifiConnected(),
            isAirplaneModeOn = statusProvider.isAirplaneModeOn()
        )
    }
}