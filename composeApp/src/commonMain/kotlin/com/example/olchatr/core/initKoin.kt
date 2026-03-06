package com.example.olchatr.core

import com.example.olchatr.feature.favorite.data.ProductLocalDataSource
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
    }.also {
        it.koin.get<ProductLocalDataSource>()
    }
}
