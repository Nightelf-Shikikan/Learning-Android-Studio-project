package com.example.myapplication2.data.repository

import com.example.myapplication2.data.model.ApiItem
import com.example.myapplication2.data.model.OpenLibraryResponse
import com.example.myapplication2.data.network.ApiService

class ApiRepository(private val apiService: ApiService) {
    suspend fun fetchItems(): List<ApiItem> {
    val response = apiService.getItems("")  // fetch all or default
    return response.docs
}
    suspend fun searchItems(query: String): OpenLibraryResponse {
        return apiService.searchBooks(query) // this returns the OpenLibraryResponse
    }
}
