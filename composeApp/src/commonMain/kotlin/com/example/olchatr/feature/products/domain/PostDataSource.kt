package com.example.olchatr.feature.products.domain

import com.example.olchatr.core.network.NetworkError
import com.example.olchatr.core.network.ProductDto
import com.example.olchatr.core.network.Result

interface ProductsDataSource {
    suspend fun getProducts(): Result<List<ProductDto>, NetworkError>
}