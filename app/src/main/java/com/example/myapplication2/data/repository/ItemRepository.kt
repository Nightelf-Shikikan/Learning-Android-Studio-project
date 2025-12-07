package com.example.myapplication2.data.repository

import com.example.myapplication2.data.database.dao.ItemDao
import com.example.myapplication2.data.model.Item
import kotlinx.coroutines.flow.Flow


class ItemRepository(private val dao: ItemDao) {

    val allItems = dao.getAllItems() // Flow<List<Item>>

    suspend fun insert(item: Item) = dao.insertItem(item)
    suspend fun update(item: Item) = dao.updateItem(item)
    suspend fun delete(item: Item) = dao.deleteItem(item)

    // Return Flow<List<Item>>
    fun searchItems(query: String): Flow<List<Item>> {
        return dao.search(query)
    }
}