package com.example.olchatr.feature.favorite.presentation

import com.example.olchatr.feature.products.presentation.Product

sealed interface FavoritesUiState {
    data object Loading : FavoritesUiState
    data object Empty : FavoritesUiState
    data class Success(val products: List<Product>) : FavoritesUiState
}