package com.example.myapplication2.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import com.example.myapplication2.data.model.CatalogItem
import com.example.myapplication2.data.repository.CatalogItemRepository
import com.example.myapplication2.ui.catalog.CatalogServerResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val repository: CatalogItemRepository
) : ViewModel() {

    private val _state = MutableStateFlow<CatalogServerResult>(CatalogServerResult.Idle)
    val state: StateFlow<CatalogServerResult> = _state.asStateFlow()

    private val _items = MutableStateFlow<List<CatalogItem>>(emptyList())
    val items = _items.asStateFlow()

    private val _basket = MutableStateFlow<List<CatalogItem>>(emptyList())
    val basket = _basket.asStateFlow()



    fun sendRequest() {
        // Prevent multiple simultaneous loads
        if (_state.value is CatalogServerResult.Loading) return

        viewModelScope.launch {
            _state.value = CatalogServerResult.Loading

            val result = repository.getItems()
            val data = result.getOrNull() ?: emptyList()

            // Only update items if list is empty
            if (_items.value.isEmpty()) {
                _items.value = data
            }

            _state.value = CatalogServerResult.Success(_items.value)
        }
    }

    fun addToBasket(item: CatalogItem) {
        _basket.value = _basket.value + item
    }
    fun removeFromBasket(item: CatalogItem) {
        _basket.value = _basket.value - item
    }
    fun refreshBasket() {
        // Just re-emit the basket list so the fragment updates
        _basket.value = _basket.value
    }

    fun getBasketItem(id: Int): CatalogItem? {
        return _basket.value.firstOrNull { it.id == id }
    }

    fun updateBasketItem(updated: CatalogItem) {
        _basket.value = _basket.value.map { item ->
            if (item.id == updated.id) updated else item
        }
    }
    fun updateCatalogItem(updated: CatalogItem) {
        _items.value = _items.value.map { if (it.id == updated.id) updated else it }
    }
}