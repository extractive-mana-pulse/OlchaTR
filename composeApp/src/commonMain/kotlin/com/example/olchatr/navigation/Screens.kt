package com.example.olchatr.navigation

import kotlinx.serialization.Serializable

interface Screens {

    @Serializable
    data object Home: Screens {
        @Serializable
        data class ProductDetail(val productId: Int): Screens
    }
    @Serializable
    data object Basket : Screens
    @Serializable
    data object Favorite: Screens
    @Serializable
    data object Profile: Screens
}