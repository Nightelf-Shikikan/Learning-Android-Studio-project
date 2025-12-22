package com.example.myapplication2.ui.catalog

import com.example.myapplication2.data.model.CatalogItem

sealed class CatalogServerResult {
    data object Idle : CatalogServerResult()
    data object Loading : CatalogServerResult()
    // Change String to List<String> here:
    data class Success(val message: List<CatalogItem>) : CatalogServerResult()
    data class Error(val message: String) : CatalogServerResult()
}