package com.example.olchatr.feature.products.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.olchatr.core.components.LoadingScreen
import com.example.olchatr.core.components.isScrollingUp
import com.example.olchatr.feature.products.presentation.components.BottomFloatingBar
import com.example.olchatr.feature.products.presentation.components.CategorySection
import com.example.olchatr.feature.products.presentation.components.HeaderSection
import com.example.olchatr.feature.products.presentation.components.ProductGrid
import com.example.olchatr.feature.products.presentation.components.SearchSection

@Composable
fun ProductsScreen(
    uiState: ProductsUiState,
    isRefreshing: Boolean = false,
    onRefresh: () -> Unit = {},
    onFavoriteClick: (Product) -> Unit = {},
    onNavigateToFavorites: () -> Unit = {},
    onNavigateToCart: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    onNavigateToProductDetail: (Int) -> Unit = {},
    listState: LazyGridState = rememberLazyGridState()
) {
    var selectedTab by remember { mutableStateOf(0) }
    val isScrollingUp by listState.isScrollingUp()
    val pullRefreshState = rememberPullToRefreshState()

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = isScrollingUp,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                BottomFloatingBar(
                    selectedIndex = selectedTab,
                    onItemClick = { index ->
                        selectedTab = index
                        when (index) {
                            0 -> {}
                            1 -> onNavigateToFavorites()
                            2 -> onNavigateToCart()
                            3 -> onNavigateToProfile()
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
            state = pullRefreshState,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
                .background(Color(0xFFF7F7F7))
        ) {
            when (uiState) {
                is ProductsUiState.Error -> {}
                ProductsUiState.Loading -> LoadingScreen()
                is ProductsUiState.Success -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                    ) {
                        Spacer(Modifier.height(48.dp))
                        HeaderSection()
                        Spacer(Modifier.height(24.dp))
                        SearchSection()
                        Spacer(Modifier.height(24.dp))
                        CategorySection()
                        Spacer(Modifier.height(24.dp))
                        ProductGrid(
                            products = uiState.products,
                            favoriteIds = uiState.favoriteIds,
                            state = listState,
                            onFavoriteClick = onFavoriteClick,
                            onProductClick = {
                                onNavigateToProductDetail(it.id)
                            }
                        )
                    }
                }
            }
        }
    }
}