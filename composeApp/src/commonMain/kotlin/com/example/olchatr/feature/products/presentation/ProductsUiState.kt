package com.example.olchatr.feature.products.presentation

sealed interface ProductsUiState {
    data object Loading : ProductsUiState
    data class Error(val message: String) : ProductsUiState
    data class Success(
        val products: List<Product>,
        val favoriteIds: Set<Int>,
        val isOffline: Boolean = false
    ) : ProductsUiState
}