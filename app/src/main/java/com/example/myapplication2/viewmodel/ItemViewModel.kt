package com.example.myapplication2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.data.model.Item
import com.example.myapplication2.data.repository.ItemRepository
import kotlinx.coroutines.launch

class ItemViewModel(private val repository: ItemRepository) : ViewModel() {

    val items = repository.allItems.asLiveData()

    fun addItem(content: String) {
        viewModelScope.launch {
            repository.insert(Item(content = content, checkbox = false))
        }
    }

    fun toggleItem(item: Item, checked: Boolean) {
        viewModelScope.launch {
            repository.update(item.copy(checkbox = checked))
        }
    }

    fun delete(item: Item) {
        viewModelScope.launch {
            repository.delete(item)
        }
    }
    fun update(item: Item) = viewModelScope.launch {
        repository.update(item)
    }
}