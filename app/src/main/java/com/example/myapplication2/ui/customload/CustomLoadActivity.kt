package com.example.myapplication2.ui.customload
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication2.databinding.ActivityCustomLoadBinding

import com.example.myapplication2.viewmodel.CustomLoadViewModel

class CustomLoadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomLoadBinding
    private val viewModel: CustomLoadViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCustomLoadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loadingView.show("Please wait...")

        binding.loadingView.hide()

        observeViewModel()
        viewModel.loadData()

        setupClicks()
    }

    private fun setupClicks() {
        binding.btnCustomLoad.setOnClickListener {
            showLoading()
            binding.loadingView.postDelayed({
                hideLoading() // hide after 3 seconds
            }, 3000)
        }
    }
    private fun showLoading() {
        binding.loadingView.show("Loading, please wait...")
        binding.btnCustomLoad.visibility = View.INVISIBLE
        // Optional: hide after delay (simulation)
        binding.loadingView.postDelayed({
            binding.loadingView.hide()
        }, 3000)

    }
    private fun hideLoading() {
        binding.btnCustomLoad.visibility = View.VISIBLE
        binding.loadingView.hide()
    }

    private fun observeViewModel() {
        viewModel.message.observe(this) { text ->
            binding.textViewMessage.text = text
        }
    }
}