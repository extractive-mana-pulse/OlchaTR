package com.example.olchatr.feature.favorite.data

import com.example.olchatr.core.database.FavoriteEntity
import com.example.olchatr.core.database.ProductDao
import com.example.olchatr.core.mappers.toDomain
import com.example.olchatr.core.mappers.toEntity
import com.example.olchatr.feature.products.presentation.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductLocalDataSource(private val productDao: ProductDao) {

    suspend fun saveProducts(products: List<Product>) {
        productDao.insertAll(products.map { it.toEntity() })
    }

    fun observeFavorites(): Flow<List<Int>> {
        return productDao.observeFavorites().map { list ->
            list.map { it.productId }
        }
    }

    suspend fun toggleFavorite(productId: Int) {
        if (productDao.isFavorite(productId)) {
            productDao.deleteFavorite(productId)
        } else {
            productDao.insertFavorite(FavoriteEntity(productId))
        }
    }

    fun observeProductById(productId: Int): Flow<Product?> {
        return productDao.observeProductById(productId).map { it?.toDomain() }
    }

    fun observeAllProducts(): Flow<List<Product>> {
        return productDao.observeAllProducts().map { list ->
            list.map { it.toDomain() }
        }
    }
}