package com.example.myapplication2.ui.api

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication2.data.network.ApiClient
import com.example.myapplication2.databinding.ActivityApiBinding

import kotlinx.coroutines.launch
import com.example.myapplication2.viewmodel.ApiViewModelFactory
import com.example.myapplication2.data.repository.ApiRepository
import com.example.myapplication2.viewmodel.ApiViewModel

class ApiActivity : AppCompatActivity() {

    private lateinit var adapter: ApiAdapter
    private lateinit var binding: ActivityApiBinding
    private val viewModel: ApiViewModel by viewModels {
        ApiViewModelFactory(ApiRepository(ApiClient.apiService))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityApiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ApiAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter



        // Observe items
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.items.collect { items ->
                    adapter.submitList(items)
                }
            }
        }

        // Observe loading & errors if you want
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loading.collect { isLoading ->
                    binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.error.collect { err ->
                    binding.errorTextView.text = err ?: ""
                }
            }
        }


        binding.btnFetchAll.setOnClickListener {
            viewModel.loadItems()  // this triggers the repository -> API -> updates StateFlow
        }
    }
}


