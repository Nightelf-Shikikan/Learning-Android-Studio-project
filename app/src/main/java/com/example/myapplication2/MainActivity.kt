package com.example.myapplication2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication2.databinding.ActivityMainBinding

// ---------------- MainActivity -----------------


private lateinit var binding: ActivityMainBinding

private lateinit var viewModel: ItemViewModel
private lateinit var adapter: ItemAdapter

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel and Repository
        val repository = ItemRepository(DatabaseInstance.getDatabase(this).itemDao())
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