package com.example.myapplication2.ui.test1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication2.MyApp
import com.example.myapplication2.R
import com.example.myapplication2.di.DaggerAppComponent
import com.example.myapplication2.viewmodel.Test1ViewModel
import javax.inject.Inject

class Test1Activity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: Test1ViewModel  // Injected ViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test1)

        // Inject dependencies from Application
        (application as MyApp).appComponent.inject(this)

        // Use ViewModel
        viewModel.sayHello()
    }
}