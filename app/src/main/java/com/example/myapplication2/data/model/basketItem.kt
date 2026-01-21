package com.example.myapplication2.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basket_items")
data class BasketItem(
    @PrimaryKey(autoGenerate = true) val basketId: Int = 0,
    val itemId: Int,
    val content: String
)