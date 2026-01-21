package com.example.myapplication2.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import com.example.myapplication2.MyApp
import com.example.myapplication2.R
import com.example.myapplication2.data.repository.ItemAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        // Inject dependencies
        (application as MyApp).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)

        // Show basket immediately
        supportFragmentManager.beginTransaction()
            .replace(R.id.basketContainer, BasketFragment())
            .commit()

        val catalogContainer = findViewById<View>(R.id.container)

        val fetchButton = findViewById<Button>(R.id.buttonFetch)
        fetchButton.setOnClickListener {
            catalogContainer.visibility = View.VISIBLE

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CatalogFragment())
                .commit()
            viewModel.fetchItems()
            fetchButton.visibility = View.GONE
        }
    }
}