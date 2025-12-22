package com.example.myapplication2.ui.catalog

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.myapplication2.viewmodel.CatalogViewModel
import kotlinx.coroutines.launch
import com.example.myapplication2.R
import com.example.myapplication2.data.model.CatalogItem
import com.example.myapplication2.databinding.ActivityCatalogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatalogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatalogBinding
    private val viewModel: CatalogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load both fragments **once**
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, CatalogFragment())
            .commit()

        supportFragmentManager.beginTransaction()
            .replace(R.id.basketFragmentContainer, BasketFragment())
            .commit()

        // Button actions
        binding.CatalogLoadButton.setOnClickListener {
            viewModel.sendRequest() // loads catalog items into CatalogFragment
        }

        binding.basketLoadButton.setOnClickListener {
            // Just refresh basket list; no need to replace fragment
            viewModel.refreshBasket()
        }
    }
}



