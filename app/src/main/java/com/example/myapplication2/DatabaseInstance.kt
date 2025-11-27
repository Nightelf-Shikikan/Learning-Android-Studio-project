package com.example.myapplication2

import android.content.Context
import androidx.room.Room

object DatabaseInstance {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val db = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "item_database"
            ).build()
            INSTANCE = db
            db
        }
    }
}