package com.example.myapplication2.data.repository

import androidx.lifecycle.LiveData
import com.example.myapplication2.data.database.AppDatabase
import com.example.myapplication2.data.database.dao.BasketDao
import com.example.myapplication2.data.database.dao.ItemDao
import com.example.myapplication2.data.model.BasketItem
import com.example.myapplication2.data.model.Item
import javax.inject.Inject

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemRepositoryImpl @Inject constructor(
    private val itemDao: ItemDao,
    private val basketDao: BasketDao

) : ItemRepository {


    override fun getCatalogItems() =
        itemDao.getCatalogItems()

    override fun getBasketItems(): LiveData<List<BasketItem>> {
        return basketDao.getBasketItems()
    }

    override fun fetchItems() {
        CoroutineScope(Dispatchers.IO).launch {

            itemDao.insertItems(
                listOf(
                    Item(1, "Apple", false),
                    Item(2, "Banana", false),
                    Item(3, "Orange", false)
                )
            )
        }
    }

    override fun clearItems() {
        CoroutineScope(Dispatchers.IO).launch {
            itemDao.clearAll()

        }
    }


    override fun addToBasket(item: Item) {
        CoroutineScope(Dispatchers.IO).launch {
            basketDao.insertBasketItem(
                BasketItem(
                    itemId = item.id,
                    content = item.content
                )
            )
        }
    }

    override fun removeFromBasket(basketItem: BasketItem) {
        CoroutineScope(Dispatchers.IO).launch {
            basketDao.delete(basketItem.basketId)
        }
    }
}