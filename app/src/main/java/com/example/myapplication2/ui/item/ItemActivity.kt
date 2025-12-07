package com.example.myapplication2.ui.item

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication2.data.database.AppDatabase
import com.example.myapplication2.data.model.Item
import com.example.myapplication2.viewmodel.ItemViewModel
import com.example.myapplication2.viewmodel.ItemViewModelFactory
import com.example.myapplication2.data.repository.ItemRepository
import com.example.myapplication2.databinding.ActivityItemBinding
import com.example.myapplication2.ui.search.SearchAdapter
import com.example.myapplication2.ui.search.SearchViewModel
import com.example.myapplication2.ui.search.SearchViewModelFactory
import kotlinx.coroutines.launch

class ItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemBinding

    private lateinit var viewModel: ItemViewModel
    private lateinit var adapter: ItemAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel and Repository
        val repository = ItemRepository(AppDatabase.getInstance(this).itemDao())

        val factory = ItemViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[ItemViewModel::class.java]

        // Setup RecyclerView
        adapter = ItemAdapter(viewModel)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe LiveData
        viewModel.items.observe(this) { items ->
            adapter.setItems(items)
        }
        viewModel.items.observe(this) { items ->
            adapter.submitList(items)
        }
        // Add new item
        binding.btnAdd.setOnClickListener {
            val content = binding.etNewItem.text.toString()
            if (content.isNotEmpty()) {
                viewModel.addItem(content)
                binding.etNewItem.text.clear()
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()

    }
}