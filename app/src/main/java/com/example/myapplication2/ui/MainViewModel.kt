package com.example.myapplication2.ui


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication2.data.model.BasketItem
import com.example.myapplication2.data.model.Item
import com.example.myapplication2.data.repository.ItemRepository
import com.example.myapplication2.data.repository.ItemRepositoryImpl
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: ItemRepository
) : ViewModel() {


    val catalogItems: LiveData<List<Item>> = repository.getCatalogItems()
    val basketItems: LiveData<List<Item>> = repository.getBasketItems()


    private val _basketItems = MutableLiveData<List<Item>>(emptyList())


    fun fetchItems() = repository.fetchItems()

    fun clearItems() {
        repository.clearItems()
    }

    fun addToBasket(item: Item) = repository.addToBasket(item)

    fun removeFromBasket(item: BasketItem) = repository.removeFromBasket(item)
}

