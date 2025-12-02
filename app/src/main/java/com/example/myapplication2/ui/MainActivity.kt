package com.example.myapplication2.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.Lifecycle

import com.example.myapplication2.databinding.ActivityMainBinding
import com.example.myapplication2.model.ResultState
import kotlinx.coroutines.launch
import com.example.myapplication2.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // If you prefer ViewModelProvider: ViewModelProvider(this).get(MainViewModel::class.java)
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoad.setOnClickListener {
            viewModel.loadData()
        }

        // Collect the StateFlow using repeatOnLifecycle for safety
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is ResultState.Idle -> {
                            binding.progress.visibility = android.view.View.GONE
                            binding.tvResult.text = "Press Load"
                            binding.btnLoad.isEnabled = true
                        }
                        is ResultState.Loading -> {
                            binding.progress.visibility = android.view.View.VISIBLE
                            binding.tvResult.text = "Loading..."
                            binding.btnLoad.isEnabled = false
                        }
                        is ResultState.Success -> {
                            binding.progress.visibility = android.view.View.GONE
                            binding.tvResult.text = state.data
                            binding.btnLoad.isEnabled = true
                        }
                        is ResultState.Error -> {
                            binding.progress.visibility = android.view.View.GONE
                            binding.tvResult.text = "Error: ${state.message}"
                            binding.btnLoad.isEnabled = true
                        }
                    }
                }
            }
        }
    }
}