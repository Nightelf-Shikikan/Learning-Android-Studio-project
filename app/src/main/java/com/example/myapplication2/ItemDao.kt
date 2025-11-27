package com.example.myapplication2

import androidx.room.*
import kotlinx.coroutines.flow.Flow



@Dao
interface ItemDao {

    @Query("SELECT * FROM items")
    fun getAllItems(): Flow<List<Item>>

    @Insert
    suspend fun insertItem(item: Item)

    @Update
    suspend fun updateItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)
}

