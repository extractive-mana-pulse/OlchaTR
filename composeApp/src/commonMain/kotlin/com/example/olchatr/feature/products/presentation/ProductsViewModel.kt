package com.example.olchatr.feature.products.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.olchatr.core.mappers.toDomain
import com.example.olchatr.core.network.Result
import com.example.olchatr.feature.favorite.data.ProductLocalDataSource
import com.example.olchatr.feature.products.domain.ProductsDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.KoinApplication.Companion.init

class ProductsViewModel(
    private val remoteDataSource: ProductsDataSource,
    private val localDataSource: ProductLocalDataSource
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    private val _isOffline = MutableStateFlow(false)
    private val _error = MutableStateFlow<String?>(null)

    val uiState: StateFlow<ProductsUiState> = combine(
        localDataSource.observeAllProducts(),
        localDataSource.observeFavorites(),
        _isLoading,
        _isOffline,
        _error
    ) { products, favorites, loading, offline, error ->
        when {
            loading && products.isEmpty() -> ProductsUiState.Loading
            error != null && products.isEmpty() -> ProductsUiState.Error(error)
            else -> ProductsUiState.Success(
                products = products,
                favoriteIds = favorites.toSet(),
                isOffline = offline
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ProductsUiState.Loading
    )

    init {
        loadProducts()
    }

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            _error.value = null

            when (val result = remoteDataSource.getProducts()) {
                is Result.Success -> {
                    localDataSource.saveProducts(
                        result.data.map { it.toDomain() }
                    )
                    _isOffline.value = false
                }
                is Result.Error -> {
                    _isOffline.value = true
                }
            }
            _isRefreshing.value = false
        }
    }
    private fun loadProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            when (val result = remoteDataSource.getProducts()) {
                is Result.Success -> {
                    localDataSource.saveProducts(
                        result.data.map { it.toDomain() }
                    )
                    _isOffline.value = false
                }
                is Result.Error -> {
                    _isOffline.value = true
                    _error.value = result.error.name
                }
            }
            _isLoading.value = false
        }
    }

    fun toggleFavorite(productId: Int) {
        viewModelScope.launch {
            localDataSource.toggleFavorite(productId)
        }
    }
}