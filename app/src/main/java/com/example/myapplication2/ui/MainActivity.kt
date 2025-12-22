package com.example.myapplication2.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication2.ui.catalog.CatalogActivity
import com.example.myapplication2.databinding.ActivityMainBinding
import com.example.myapplication2.ui.load.LoadActivity
import com.example.myapplication2.ui.api.ApiActivity
import com.example.myapplication2.ui.item.ItemActivity
import com.example.myapplication2.ui.search.SearchActivity

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Button to open FirstActivity
        binding.buttonOpenFirst.setOnClickListener {
            val intent = Intent(this, ItemActivity::class.java)
            startActivity(intent)
        }

        // Button to open SecondActivity
        binding.buttonOpenSecond.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        // Button to open ThirdActivity
        binding.buttonOpenThird.setOnClickListener {
            val intent = Intent(this, ApiActivity::class.java)
            startActivity(intent)
        }

        // Button to open LoadActivity
        binding.buttonOpenLoad.setOnClickListener {
            val intent = Intent(this, LoadActivity::class.java)
            startActivity(intent)
        }
        // Button to open CatalogActivity
        binding.buttonOpenCatalog.setOnClickListener {
            val intent = Intent(this, CatalogActivity::class.java)
            startActivity(intent)
        }


    }
}