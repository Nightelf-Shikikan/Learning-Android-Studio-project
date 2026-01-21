package com.example.myapplication2.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication2.data.model.Item
import kotlinx.coroutines.flow.Flow
import androidx.room.OnConflictStrategy
import com.example.myapplication2.data.database.AppDatabase
import com.example.myapplication2.data.model.BasketItem

@Dao
interface ItemDao {


    @Query("SELECT * FROM items")
    fun getCatalogItems(): LiveData<List<Item>>

    @Query("SELECT * FROM items WHERE checkbox = 1")
    fun getBasketItems(): LiveData<List<Item>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<Item>)

    @Query("UPDATE items SET checkbox = 1 WHERE id = :id")
    suspend fun moveToBasket(id: Int)

    @Query("DELETE FROM items WHERE id = :itemId AND inBasket = 1")
    suspend fun removeFromBasket(itemId: Int)

    @Query("DELETE FROM items")
    suspend fun clearAll()

    @Query("SELECT * FROM items")
    fun getItems(): LiveData<List<Item>>

    @Insert
    suspend fun insertBasketItem(item: Item)

    @Update
    suspend fun updateItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)


    @Query("SELECT * FROM items WHERE content LIKE '%' || :query || '%'")
    fun search(query: String): Flow<List<Item>>}

