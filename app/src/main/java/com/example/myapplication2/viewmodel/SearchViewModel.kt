
package com.example.myapplication2.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.data.model.Item
import com.example.myapplication2.data.repository.ItemRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: ItemRepository) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Updated: searchResults now directly flatMapLatest to repository.searchItems(query)
    val searchResults: StateFlow<List<Item>> = _searchQuery
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            if (query.isBlank()) {
                flowOf(emptyList()) // empty list if search query is blank
            } else {
                repository.searchItems(query) // Flow<List<Item>> from repository
            }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun setQuery(query: String) {
        _searchQuery.value = query
    }

    // Optional: add update/delete if you want editable checkboxes
    fun update(item: Item) = viewModelScope.launch {
        repository.update(item)
    }

    fun delete(item: Item) = viewModelScope.launch {
        repository.delete(item)
    }
}