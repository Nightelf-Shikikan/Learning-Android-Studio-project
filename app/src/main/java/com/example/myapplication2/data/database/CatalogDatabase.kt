package com.example.myapplication2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication2.data.database.dao.CatalogDao
import com.example.myapplication2.data.model.CatalogItemEntity

@Database(
    entities = [CatalogItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun catalogDao(): CatalogDao
}