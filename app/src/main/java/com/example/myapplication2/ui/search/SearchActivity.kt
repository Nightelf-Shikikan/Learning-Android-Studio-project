package com.example.myapplication2.ui.search

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle

import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication2.data.database.AppDatabase

import com.example.myapplication2.data.repository.ItemRepository
import com.example.myapplication2.databinding.ActivitySearchBinding
import androidx.core.widget.doOnTextChanged
import kotlinx.coroutines.flow.onStart

import kotlinx.coroutines.launch

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    private lateinit var adapter: SearchAdapter

    private val viewModel: SearchViewModel by viewModels {
        val dao = AppDatabase.getInstance(applicationContext).itemDao()

        val repository = ItemRepository(dao)
        SearchViewModelFactory(repository) // just return the factory
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = SearchAdapter(viewModel)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.searchEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.setQuery(text.toString())
        }
        // Show error
        binding.errorTextView.text = "No items found"
        binding.errorTextView.visibility = View.VISIBLE

// Hide error when results exist
        binding.errorTextView.visibility = View.GONE

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchResults.collect { items ->
                    adapter.submitList(items)

                    // Show error if list is empty and query is not blank
                    if (items.isEmpty() && viewModel.searchQuery.value.isNotBlank()) {
                        binding.errorTextView.text = "No items found"
                        binding.errorTextView.visibility = View.VISIBLE
                    } else {
                        binding.errorTextView.visibility = View.GONE
                    }
                }
            }
        }
    }
}