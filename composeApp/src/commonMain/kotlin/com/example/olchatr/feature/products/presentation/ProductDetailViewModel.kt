package com.example.olchatr.feature.products.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.olchatr.feature.favorite.data.ProductLocalDataSource
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class ProductDetailViewModel(
    productId: Int,
    localDataSource: ProductLocalDataSource
) : ViewModel() {

    val product = localDataSource.observeProductById(productId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )
}