package com.example.olchatr.feature.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.olchatr.feature.favorite.data.ProductLocalDataSource
import com.example.olchatr.feature.products.presentation.Product
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val localDataSource: ProductLocalDataSource
) : ViewModel() {

    val uiState: StateFlow<FavoritesUiState> = combine(
        localDataSource.observeAllProducts(),
        localDataSource.observeFavorites()
    ) { posts, favoriteIds ->
        val favoritePosts = posts.filter { it.id in favoriteIds }
        if (favoritePosts.isEmpty()) FavoritesUiState.Empty
        else FavoritesUiState.Success(favoritePosts)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = FavoritesUiState.Loading
    )

    fun toggleFavorite(post: Product) {
        viewModelScope.launch {
            localDataSource.toggleFavorite(post.id)
        }
    }
}