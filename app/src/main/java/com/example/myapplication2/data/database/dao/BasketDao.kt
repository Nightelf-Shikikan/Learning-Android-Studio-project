package com.example.myapplication2.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication2.data.model.BasketItem

@Dao
interface BasketDao {
    @Query("SELECT * FROM basket_items")
    fun getBasketItems(): LiveData<List<BasketItem>>

    @Insert
    suspend fun insertBasketItem(item: BasketItem)

    @Delete
    suspend fun removeFromBasket(item: BasketItem)

    @Query("DELETE FROM basket_items")
    suspend fun clearAll()

    @Query("DELETE FROM basket_items WHERE basketId = :basketId")
    suspend fun delete(basketId: Int)
}