package com.example.myapplication2.ui.load

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle

import com.example.myapplication2.databinding.ActivityLoadBinding


import com.example.myapplication2.viewmodel.LoadViewModel

import kotlin.getValue
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.myapplication2.data.load.ServerResult

import kotlinx.coroutines.launch


class LoadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoadBinding
    private val viewModel: LoadViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoad.setOnClickListener {
            Log.d("LOAD_ACTIVITY", "Button clicked!")
            viewModel.sendRequest()
        }
        fun observeViewModel() {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.serverResult.collect { result ->
                        when (result) {
                            is ServerResult.Idle -> {
                                binding.progressBar.visibility = View.GONE
                                binding.btnLoad.visibility = View.VISIBLE
                                binding.tvResult.text = ""
                            }

                            is ServerResult.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                                binding.btnLoad.visibility = View.GONE
                            }

                            is ServerResult.Success -> {
                                binding.progressBar.visibility = View.GONE
                                binding.btnLoad.visibility = View.VISIBLE
                                binding.tvResult.text = result.message
                            }

                            is ServerResult.Error -> {
                                binding.progressBar.visibility = View.GONE
                                binding.btnLoad.visibility = View.VISIBLE
                                binding.tvResult.text = result.errorMessage
                            }
                        }
                    }
                }
            }
        }

        observeViewModel()
    }
}










