package com.example.myapplication2.ui.catalog

sealed class CatalogState {
    object Idle : CatalogState()
    object Loading : CatalogState()
    data class Success(val items: List<String>) : CatalogState()
    data class Error(val message: String) : CatalogState()
}