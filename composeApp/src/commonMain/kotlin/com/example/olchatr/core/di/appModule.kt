package com.example.olchatr.core.di

import com.example.olchatr.createHttpClient
import com.example.olchatr.feature.favorite.data.ProductLocalDataSource
import com.example.olchatr.feature.favorite.presentation.FavoritesViewModel
import com.example.olchatr.feature.products.data.ProductsDataSourceImpl
import com.example.olchatr.feature.products.domain.ProductsDataSource
import com.example.olchatr.feature.products.presentation.ProductDetailViewModel
import com.example.olchatr.feature.products.presentation.ProductsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { createHttpClient() }

    single { ProductLocalDataSource(get()) }

    single<ProductsDataSource> { ProductsDataSourceImpl(get()) }

    viewModel { FavoritesViewModel(get()) }
    viewModel { ProductsViewModel(get(), get()) }
    viewModel { (productId: Int) ->
        ProductDetailViewModel(
            productId = productId,
            localDataSource = get()
        )
    }

}