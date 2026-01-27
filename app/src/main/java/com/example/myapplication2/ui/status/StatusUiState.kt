package com.example.myapplication2.ui.status

data class StatusUiState(
    val batteryPercent: Int = 0,
    val isWifiConnected: Boolean = false,
    val isAirplaneModeOn: Boolean = false
)