package com.example.myapplication2

import androidx.lifecycle.*
import kotlinx.coroutines.launch

import androidx.lifecycle.asLiveData


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

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            repository.delete(item)
        }
    }
}