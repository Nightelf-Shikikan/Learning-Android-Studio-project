package com.example.myapplication2.data.network

import com.example.myapplication2.data.model.ApiItem
import com.example.myapplication2.data.model.OpenLibraryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String
    ): OpenLibraryResponse
}