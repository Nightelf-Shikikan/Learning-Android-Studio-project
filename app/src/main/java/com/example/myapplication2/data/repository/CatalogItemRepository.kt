package com.example.myapplication2.data.repository


import com.example.myapplication2.data.model.CatalogItem
import kotlinx.coroutines.delay

import javax.inject.Inject
import kotlin.random.Random


class CatalogItemRepository @Inject constructor() {

    suspend fun getItems(): Result<List<CatalogItem>> {
        delay(1500)
        return Result.success(
            listOf(
                CatalogItem(1, "Apple", "Fresh red apple", 0.99),
                CatalogItem(2, "Banana", "Ripe yellow banana", 1.20),
                CatalogItem(3, "Orange", "Sweet orange", 1.50),
                CatalogItem(4, "Milk", "1L whole milk", 2.30)
            )
        )
    }
}