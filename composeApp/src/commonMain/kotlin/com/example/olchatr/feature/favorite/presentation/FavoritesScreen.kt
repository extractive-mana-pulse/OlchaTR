package com.example.olchatr.feature.favorite.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.olchatr.core.components.LoadingScreen
import com.example.olchatr.feature.favorite.presentation.components.EmptyFavoriteScreen
import com.example.olchatr.feature.favorite.presentation.components.FavoriteItem
import com.example.olchatr.feature.products.presentation.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    uiState: FavoritesUiState,
    onFavoriteClick: (Product) -> Unit,
    onBack: () -> Unit,
    onFavoriteToggle: (Product) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Favorites",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier
                            .background(
                                color = Color.White,
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = null
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        when (uiState) {
            is FavoritesUiState.Loading -> LoadingScreen()
            is FavoritesUiState.Empty -> EmptyFavoriteScreen(innerPadding)
            is FavoritesUiState.Success -> {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxSize()
                        .padding(innerPadding),
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        items(uiState.products) { product ->

                            FavoriteItem(
                                product = product,
                                onFavoriteClick = { onFavoriteClick(product) },
                                onFavoriteToggle = { onFavoriteToggle(product) }
                            )
                        }
                    }
                }
            }
        }
    }
}