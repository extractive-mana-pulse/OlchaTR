package com.example.olchatr.navigation

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.olchatr.feature.basket.BasketScreen
import com.example.olchatr.feature.favorite.presentation.FavoritesScreen
import com.example.olchatr.feature.favorite.presentation.FavoritesViewModel
import com.example.olchatr.feature.products.presentation.ProductDetailScreen
import com.example.olchatr.feature.products.presentation.ProductDetailViewModel
import com.example.olchatr.feature.products.presentation.ProductsScreen
import com.example.olchatr.feature.products.presentation.ProductsUiState
import com.example.olchatr.feature.products.presentation.ProductsViewModel
import com.example.olchatr.feature.profile.ProfileScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home
    ) {
        composable<Screens.Home> {
            val vm = koinViewModel<ProductsViewModel>()
            val uiState by vm.uiState.collectAsStateWithLifecycle()
            val refreshing by vm.isRefreshing.collectAsStateWithLifecycle()
            val listState = rememberLazyGridState()

            ProductsScreen(
                uiState = uiState,
                isRefreshing = refreshing,
                listState = listState,
                onRefresh = { vm.refresh() },
                onNavigateToProductDetail = { productId ->
                    navController.navigate(Screens.Home.ProductDetail(productId))
                },
                onFavoriteClick = { vm.toggleFavorite(it.id) },
                onNavigateToFavorites = {
                    navController.navigate(Screens.Favorite)
                },
                onNavigateToCart = {
//                    navController.navigate(Screens.Basket)
                },
                onNavigateToProfile = {
//                    navController.navigate(Screens.Profile)
                },
            )
        }
        composable<Screens.Home.ProductDetail> { backStackEntry ->
            val args = backStackEntry.toRoute<Screens.Home.ProductDetail>()
            val vm = koinViewModel<ProductDetailViewModel>(
                parameters = { parametersOf(args.productId) }
            )
            val product by vm.product.collectAsStateWithLifecycle()

            val productVm = koinViewModel<ProductsViewModel>()
            val uiState by productVm.uiState.collectAsStateWithLifecycle()

            val favoriteIds = when (val state = uiState) {
                is ProductsUiState.Success -> state.favoriteIds
                else -> emptySet()
            }

            ProductDetailScreen(
                product = product,
                onBack = { navController.navigateUp() },
                onFavoriteToggle = { productVm.toggleFavorite(args.productId) },
                isFavorite = favoriteIds
            )
        }
        composable<Screens.Basket> {
            BasketScreen()
        }
        composable<Screens.Favorite> {
            val viewModel = koinViewModel<FavoritesViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            FavoritesScreen(
                uiState = uiState,
                onFavoriteClick = { product ->
                    navController.navigate(Screens.Home.ProductDetail(
                        productId = product.id
                    ))
                },
                onBack = { navController.navigateUp() },
                onFavoriteToggle = {
                    viewModel.toggleFavorite(it)
                }
            )
        }
        composable<Screens.Profile> {
            ProfileScreen()
        }
    }
}