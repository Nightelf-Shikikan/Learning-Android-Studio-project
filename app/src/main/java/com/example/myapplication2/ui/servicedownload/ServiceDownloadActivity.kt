package com.example.myapplication2.ui.servicedownload


import android.content.BroadcastReceiver
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View

import androidx.core.content.ContextCompat
import com.example.myapplication2.MyApp
import com.example.myapplication2.R
import android.widget.Button
import android.widget.ProgressBar
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.myapplication2.viewmodel.ServiceDownlaodViewModel
import javax.inject.Inject


class ServiceDownloadActivity : AppCompatActivity() {


    private lateinit var progressBar: ProgressBar
    private lateinit var progressReceiver: BroadcastReceiver

    @Inject
    lateinit var viewModel: ServiceDownlaodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicedownload)

        // Inject dependencies
        (application as MyApp).appComponent.inject(this)

        // Find views
        progressBar = findViewById(R.id.downloadProgressBar)
        val downloadButton = findViewById<Button>(R.id.foregroundDownload)
        val openButton = findViewById<Button>(R.id.openImageButton)

        // Setup download progress receiver
        progressReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                // This runs whenever the service broadcasts progress
                val progress = intent?.getIntExtra("progress", 0) ?: 0
                progressBar.progress = progress

                if (progress >= 100) {
                    progressBar.visibility = View.GONE
                }
            }
        }

        // Register receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(
            progressReceiver,
            IntentFilter("DOWNLOAD_PROGRESS")
        )

        // Button listeners
        downloadButton.setOnClickListener {
            // Show progress bar
            progressBar.visibility = View.VISIBLE
            progressBar.progress = 0

            startDownloadService()
        }

        openButton.setOnClickListener {
            openImageFromGallery("kitten.jpg")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(progressReceiver)
    }
    private fun openImageFromGallery(fileName: String = "kitten.jpg") {
        val projection = arrayOf(MediaStore.Images.Media._ID)
        val selection = "${MediaStore.Images.Media.DISPLAY_NAME} = ?"
        val selectionArgs = arrayOf(fileName)

        val query = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection, selection, selectionArgs, null
        )

        query?.use { cursor ->
            if (cursor.moveToFirst()) {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
                val contentUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    setDataAndType(contentUri, "image/*")
                    flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                }
                startActivity(intent)
            }
        }
    }
    private fun startDownloadService() {
        val intent = Intent(this, DownloadService::class.java).apply {
            putExtra(
                DownloadService.EXTRA_URL,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Juvenile_Ragdoll.jpg/1280px-Juvenile_Ragdoll.jpg"
            )
        }
        ContextCompat.startForegroundService(this, intent)
    }

}

