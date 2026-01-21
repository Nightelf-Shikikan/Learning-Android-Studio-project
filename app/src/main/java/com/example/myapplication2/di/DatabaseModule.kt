package com.example.myapplication2.di

import android.app.Application
import androidx.room.Room
import com.example.myapplication2.data.database.AppDatabase
import com.example.myapplication2.data.database.dao.ItemDao

import com.example.myapplication2.data.database.dao.BasketDao

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import androidx.room.RoomDatabase

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "app_db"
        )
            .fallbackToDestructiveMigration() // optional for dev
            .build()

    @Provides
    fun provideItemDao(db: AppDatabase): ItemDao =
        db.itemDao()

    @Provides

    fun provideBasketDao(database: AppDatabase): BasketDao = database.basketDao()

}