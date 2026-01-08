package com.example.myapplication2.data.database

import android.content.Context
import androidx.room.Room
import com.example.myapplication2.data.database.dao.CatalogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_db"
        ).build()

    @Provides
    fun provideCatalogDao(
        db: AppDatabase
    ): CatalogDao = db.catalogDao()
}