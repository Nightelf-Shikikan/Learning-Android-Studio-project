package com.example.myapplication2.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.data.model.ApiItem
import com.example.myapplication2.data.repository.ApiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ApiViewModel(private val repository: ApiRepository): ViewModel() {

    private val _items = MutableStateFlow<List<ApiItem>>(emptyList())
    val items: StateFlow<List<ApiItem>> = _items

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error


    fun loadItems() {
        viewModelScope.launch {
            Log.d("API_VM", "loadItems() started")
            _loading.value = true
            try {
                val result = repository.fetchItems()
                Log.d("API_VM", "Items received: ${result.size}")
                _items.value = result
                _error.value = null
            } catch (e: Exception) {
                Log.e("API_VM", "Error", e)
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }


}







