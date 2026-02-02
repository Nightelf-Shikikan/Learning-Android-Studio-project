package com.example.myapplication2.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import androidx.activity.viewModels
import com.example.myapplication2.MyApp
import com.example.myapplication2.R
import com.example.myapplication2.data.repository.ItemAdapter
import com.example.myapplication2.di.DaggerViewModelFactory
import com.example.myapplication2.ui.api.ApiActivity
import com.example.myapplication2.ui.catalog.CatalogActivity
import com.example.myapplication2.ui.profile.ProfileActivity
import com.example.myapplication2.ui.servicedownload.ServiceDownloadActivity
import com.example.myapplication2.ui.status.StatusActivity
import com.example.myapplication2.ui.test1.Test1Activity
import javax.inject.Inject
import com.example.myapplication2.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApp).appComponent.inject(this)

        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.openCatalogButton).setOnClickListener {
            startActivity(
                Intent(this, CatalogActivity::class.java)
            )
        }

        findViewById<Button>(R.id.button_start_status).setOnClickListener {
            startActivity(
                Intent(this, StatusActivity::class.java)
            )
        }

        findViewById<Button>(R.id.button_start_api).setOnClickListener {
            startActivity(
                Intent(this, ApiActivity::class.java)
            )
        }

        findViewById<Button>(R.id.button_start_service_download).setOnClickListener {
            startActivity(
                Intent(this, ServiceDownloadActivity::class.java)
            )
        }

        findViewById<Button>(R.id.button_start_profile).setOnClickListener {
            startActivity(
                Intent(this, ProfileActivity::class.java)
            )
        }
        val button = findViewById<Button>(R.id.button_start_second)
        button.setOnClickListener {
            // Create an Intent to start SecondActivity
            val intent = Intent(this, Test1Activity::class.java)
            // Start the activity
            startActivity(intent)
        }
        viewModel.startDailyDownload()

    }
}