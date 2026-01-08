package com.example.myapplication2.data.repository

import android.R.attr.description
import com.example.myapplication2.data.model.CatalogItem
import com.example.myapplication2.data.model.CatalogItemEntity

cimport com.example.roomdbtest.data.database.dao.CatalogItemEntity

// Convert entity → UI model
fun CatalogItemEntity.toUi(): CatalogItem {
    return CatalogItem(
        id = id,
        name = name,
        description = description,
        price = price
    )
}

// Convert UI model → entity
fun CatalogItem.toEntity(): CatalogItemEntity {
    return CatalogItemEntity(
        id = id,
        name = name,
        description = description,
        price = price
    )
}