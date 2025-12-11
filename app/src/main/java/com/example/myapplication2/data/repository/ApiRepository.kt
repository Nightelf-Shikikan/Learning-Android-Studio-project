package com.example.myapplication2.data.repository

import android.util.Log
import com.example.myapplication2.data.model.ApiItem
import com.example.myapplication2.data.model.OpenLibraryResponse
import com.example.myapplication2.data.network.ApiService

class ApiRepository(private val apiService: ApiService) {
    suspend fun fetchItems(): List<ApiItem> {
    val response = apiService.searchBooks("the")  // fetch all or default
        Log.d("API", "Docs count: ${response.docs.size}")
        return response.docs
}
    suspend fun searchItems(query: String): List<ApiItem> {
        return apiService.searchBooks(query).docs
    }
}
