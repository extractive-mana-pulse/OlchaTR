package com.example.olchatr.core.mappers

import com.example.olchatr.core.database.ProductEntity
import com.example.olchatr.core.network.ProductDto
import com.example.olchatr.feature.products.presentation.Product

fun ProductDto.toEntity() = ProductEntity(
    id = id,
    title = title,
    price = price,
    description = description,
    category = category,
    image = image,
    ratingRate = rating.rate ?: 0.0,
    ratingCount = rating.count ?: 0
)

fun ProductEntity.toDomain() = Product(
    id = id,
    title = title,
    price = price,
    description = description,
    category = category,
    image = image,
    ratingRate = ratingRate,
    ratingCount = ratingCount,
)

fun ProductDto.toDomain() = Product(
    id = id,
    title = title,
    price = price,
    description = description,
    category = category,
    image = image,
    ratingRate = rating.rate ?: 0.0,
    ratingCount = rating.count ?: 0
)


fun Product.toEntity() = ProductEntity(
    id = id,
    title = title,
    price = price,
    description = description,
    category = category,
    image = image,
    ratingRate = ratingRate,
    ratingCount = ratingCount
)