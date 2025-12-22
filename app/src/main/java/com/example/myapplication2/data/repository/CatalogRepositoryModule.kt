package com.example.myapplication2.data.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CatalogRepositoryModule {

    @Provides
    @Singleton
    fun provideCatalogServerRepository(): CatalogItemRepository = CatalogItemRepository()
}