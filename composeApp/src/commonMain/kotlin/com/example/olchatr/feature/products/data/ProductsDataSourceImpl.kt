package com.example.olchatr.feature.products.data

import com.example.olchatr.core.network.NetworkError
import com.example.olchatr.core.network.ProductDto
import com.example.olchatr.core.network.Result
import com.example.olchatr.core.network.constructUrl
import com.example.olchatr.core.network.safeCall
import com.example.olchatr.feature.products.domain.ProductsDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class ProductsDataSourceImpl(
    private val httpClient: HttpClient
) : ProductsDataSource {

    override suspend fun getProducts(): Result<List<ProductDto>, NetworkError> {
        return safeCall<List<ProductDto>> {
            httpClient.get(constructUrl("/products"))
        }
    }
}