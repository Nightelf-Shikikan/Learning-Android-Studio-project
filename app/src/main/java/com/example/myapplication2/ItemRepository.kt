package com.example.myapplication2

class ItemRepository(private val dao: ItemDao) {

    val allItems = dao.getAllItems()

    suspend fun insert(item: Item) = dao.insertItem(item)
    suspend fun update(item: Item) = dao.updateItem(item)
    suspend fun delete(item: Item) = dao.deleteItem(item)
}