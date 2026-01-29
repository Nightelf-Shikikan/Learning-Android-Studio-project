package com.example.myapplication2.data.repository

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.myapplication2.di.ApplicationContext
import com.example.myapplication2.ui.worker.DownloadWorker
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DownloadRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.UNMETERED) // Wi-Fi
        .setRequiresCharging(true)                      // device charging
        .build()
    fun enqueueDailyDownload(url: String) {

        val dailyWorkRequest = PeriodicWorkRequestBuilder<DownloadWorker>(
            24, TimeUnit.HOURS
        )
            .setInputData(workDataOf("URL" to url))
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "daily_download",
            ExistingPeriodicWorkPolicy.KEEP,
            dailyWorkRequest
        )
    }
}