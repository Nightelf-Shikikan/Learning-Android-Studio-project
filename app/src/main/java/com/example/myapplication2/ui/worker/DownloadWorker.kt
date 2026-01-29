package com.example.myapplication2.ui.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.File
import androidx.work.Constraints
import androidx.work.NetworkType

class DownloadWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {


    override suspend fun doWork(): Result {
        val url = inputData.getString("URL") ?: return Result.failure()

        return try {
            // Simple example: download content as string
            val result = downloadFile(url)
            saveToFile(result)
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    private suspend fun downloadFile(url: String): String {
        val client = okhttp3.OkHttpClient()
        val request = okhttp3.Request.Builder().url(url).build()
        val response = client.newCall(request).execute()

        if (!response.isSuccessful) throw Exception("Download failed")

        // Use the public getter method 'body()'
        val responseBody = response.body() ?: throw Exception("Empty response")
        return responseBody.string()
    }

    private fun saveToFile(data: String) {
        val file = File(applicationContext.filesDir, "daily_post.json")
        file.writeText(data)
    }
}