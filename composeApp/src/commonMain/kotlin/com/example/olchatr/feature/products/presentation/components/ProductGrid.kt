package com.example.olchatr.feature.products.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.olchatr.feature.products.presentation.Product

@Composable
fun ProductGrid(
    products: List<Product>,
    favoriteIds: Set<Int> = emptySet(),
    state: LazyGridState = rememberLazyGridState(),
    onFavoriteClick: (Product) -> Unit = {},
    onProductClick: (Product) -> Unit = {}
) {
    LazyVerticalGrid(
        state = state,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(products, key = { it.id }) { product ->
            ProductItem(
                product = product,
                isFavorite = product.id in favoriteIds,
                onFavoriteClick = { onFavoriteClick(product) },
                onProductClick = { onProductClick(product) }
            )
        }
    }
}