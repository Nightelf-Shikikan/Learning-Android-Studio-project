package com.example.myapplication2.data.repository

import androidx.lifecycle.LiveData
import com.example.myapplication2.data.database.dao.ItemDao
import com.example.myapplication2.data.model.BasketItem
import com.example.myapplication2.data.model.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


interface ItemRepository {
    fun clearItems()
    fun getCatalogItems(): LiveData<List<Item>>
    fun getBasketItems(): LiveData<List<BasketItem>>
    fun addToBasket(item: Item)
    fun removeFromBasket(basketItem: BasketItem)
    fun fetchItems()
}



