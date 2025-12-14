package com.example.myapplication2.data.repository

import android.util.Log
import com.example.myapplication2.data.model.ApiItem
import com.example.myapplication2.data.model.OpenLibraryResponse
import com.example.myapplication2.data.network.ApiService

class ApiRepository(private val apiService: ApiService) {
    suspend fun fetchItems(): List<ApiItem> {
    val response = apiService.searchBooks("harry potter")  // fetch all or default
        Log.d("API", "URL called: ${response}")
        return response.docs
}
    suspend fun searchItems(query: String): List<ApiItem> {
        return apiService.searchBooks(query).docs
    }
}
