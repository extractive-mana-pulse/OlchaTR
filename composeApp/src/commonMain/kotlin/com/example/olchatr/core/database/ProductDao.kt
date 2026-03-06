package com.example.olchatr.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(posts: List<ProductEntity>)

    @Query("SELECT * FROM favoriteentity")
    fun observeFavorites(): Flow<List<FavoriteEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favoriteentity WHERE productId = :productId)")
    suspend fun isFavorite(productId: Int): Boolean

    @Insert(onConflict = REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Query("DELETE FROM favoriteentity WHERE productId = :id")
    suspend fun deleteFavorite(id: Int)

    @Query("SELECT * FROM productentity WHERE id = :postId")
    fun observeProductById(postId: Int): Flow<ProductEntity?>

    @Query("SELECT * FROM productentity")
    fun observeAllProducts(): Flow<List<ProductEntity>>
}