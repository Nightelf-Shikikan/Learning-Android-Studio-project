package com.example.myapplication2.ui.status

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.NetworkCapabilities
import android.os.BatteryManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication2.MyApp
import com.example.myapplication2.R
import com.example.myapplication2.viewmodel.StatusViewModel
import com.example.myapplication2.viewmodel.Test1ViewModel
import javax.inject.Inject
import android.net.ConnectivityManager
import android.provider.Settings

class StatusActivity : AppCompatActivity() {




    @Inject
    lateinit var viewModel: StatusViewModel  // Injected ViewModel
    private lateinit var batteryText: TextView
    private lateinit var wifiText: TextView
    private lateinit var airplaneText: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)

        // Inject dependencies from Application
        (application as MyApp).appComponent.inject(this)

        batteryText = findViewById(R.id.batteryText)
        wifiText = findViewById(R.id.wifiText)
        airplaneText = findViewById(R.id.airplaneText)


        // Update UI using your ViewModel (LiveData or direct call)
        batteryText.text = "Battery: ${viewModel.statusProvider.getBatteryPercent()}%"
        wifiText.text = if (viewModel.statusProvider.isWifiConnected()) "Wi-Fi: Connected" else "Wi-Fi: Disconnected"
        airplaneText.text = if (viewModel.statusProvider.isAirplaneModeOn()) "Flight mode: ON" else "Flight mode: OFF"
    }
}