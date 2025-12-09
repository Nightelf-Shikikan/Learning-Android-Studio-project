package com.example.myapplication2.viewmodel

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
            _loading.value = true
            try {
                _items.value = repository.fetchItems()
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }
    fun search(query: String) {
        viewModelScope.launch {
            try {
                val response = repository.searchItems(query)
                _items.value = response.docs // assuming OpenLibraryResponse has a 'docs' list
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}









