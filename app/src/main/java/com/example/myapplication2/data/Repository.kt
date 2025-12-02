package com.example.myapplication2.data


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class Repository {

    // Simulate network/database call
    suspend fun fetchData(): String = withContext(Dispatchers.IO) {
        // simulate delay
        delay(5000L)
        // return result or throw if you want to simulate error
        "Hello from Repository at ${System.currentTimeMillis()}"
    }
}