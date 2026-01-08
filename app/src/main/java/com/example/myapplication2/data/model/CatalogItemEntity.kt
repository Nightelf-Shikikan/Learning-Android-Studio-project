package com.example.myapplication2.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "catalog_items")
data class CatalogItemEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Double,
    val description: String
)