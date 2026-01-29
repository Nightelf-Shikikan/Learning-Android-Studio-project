package com.example.myapplication2.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.example.myapplication2.utils.ToastHelper
import javax.inject.Inject
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.myapplication2.ui.servicedownload.DownloadService


class ServiceDownlaodViewModel
@Inject constructor(
    private val toastHelper: ToastHelper
) : ViewModel() {

    fun startDownload(context: Context) {
        val intent = Intent(context, DownloadService::class.java)
        ContextCompat.startForegroundService(context, intent)
    }
}