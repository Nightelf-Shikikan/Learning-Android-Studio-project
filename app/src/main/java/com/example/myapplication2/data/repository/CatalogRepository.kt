package com.example.myapplication2.data.repository

import com.example.myapplication2.data.database.dao.CatalogDao
import com.example.myapplication2.data.model.CatalogItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CatalogRepository @Inject constructor(
    private val dao: CatalogDao,
    private val api: CatalogApi // Retrofit
) {

    val catalog: Flow<List<CatalogItem>> =
        dao.observeCatalog()
            .map { entities ->
                entities.map { it.toUiModel() }
            }

    suspend fun refreshCatalog() {
        val response = api.getCatalog()

        dao.insertAll(
            response.map { it.toEntity() }
        )
    }
}