package com.example.myapplication2.ui.status

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication2.MyApp
import com.example.myapplication2.R
import com.example.myapplication2.viewmodel.StatusViewModel
import com.example.myapplication2.viewmodel.Test1ViewModel
import javax.inject.Inject

class StatusActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: StatusViewModel  // Injected ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)

        // Inject dependencies from Application
        (application as MyApp).appComponent.inject(this)

        // Use ViewModel
        viewModel.sayHello()

    }
}