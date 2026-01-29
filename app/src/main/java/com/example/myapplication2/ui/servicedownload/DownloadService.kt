package com.example.myapplication2.ui.servicedownload

import android.app.Notification

import android.app.Service
import android.content.ContentValues
import android.content.Intent
import android.os.Environment
import android.os.IBinder
import android.provider.MediaStore
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.myapplication2.R
import java.io.ByteArrayOutputStream

import java.net.HttpURLConnection
import java.net.URL

class DownloadService : Service() {

    companion object {
        const val EXTRA_URL = "extra_url"
    }
    private fun createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = android.app.NotificationChannel(
                "download_channel",
                "Download Service",
                android.app.NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Shows progress of downloads"
            }

            val manager = getSystemService(android.app.NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()

        val notification: Notification =
            NotificationCompat.Builder(this, "download_channel")
                .setContentTitle("Downloading")
                .setContentText("Service is running...")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build()

        startForeground(1, notification)

    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val url = intent?.getStringExtra(EXTRA_URL)
            ?: return START_NOT_STICKY

        Thread {
            try {
                val bytes = downloadImageWithProgress(url)
                saveImageWithProgress(bytes)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                stopSelf()
            }
        }.start()

        return START_STICKY
    }


    private fun downloadImageWithProgress(urlString: String): ByteArray {
        val url = URL(urlString)
        val connection = url.openConnection() as HttpURLConnection
        connection.connect()

        val contentLength = connection.contentLength // total size
        val input = connection.inputStream
        val output = ByteArrayOutputStream()
        val buffer = ByteArray(1024) // read 1 KB at a time
        var bytesRead: Int
        var totalRead = 0

        while (input.read(buffer).also { bytesRead = it } != -1) {
            output.write(buffer, 0, bytesRead)
            totalRead += bytesRead

            // calculate progress
            val progress = (totalRead * 100 / contentLength)
            // send progress to activity
            val intent = Intent("DOWNLOAD_PROGRESS")
            intent.putExtra("progress", progress)
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        }

        input.close()
        return output.toByteArray()
    }
    private fun saveImageWithProgress(bytes: ByteArray) {
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "kitten.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            put(MediaStore.Images.Media.IS_PENDING, 1)
        }

        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values) ?: return

        contentResolver.openOutputStream(uri)?.use { output ->
            val total = bytes.size
            var written = 0
            val chunkSize = 4096

            while (written < total) {
                val end = (written + chunkSize).coerceAtMost(total)
                output.write(bytes, written, end - written)
                written = end

                // send progress broadcast
                val progress = written * 100 / total
                sendBroadcast(Intent("DOWNLOAD_PROGRESS").apply {
                    putExtra("progress", progress)
                })

                // optional tiny delay so user sees progress
                Thread.sleep(20)
            }
        }

        values.clear()
        values.put(MediaStore.Images.Media.IS_PENDING, 0)
        contentResolver.update(uri, values, null, null)
    }

    override fun onBind(intent: Intent?): IBinder? = null
}