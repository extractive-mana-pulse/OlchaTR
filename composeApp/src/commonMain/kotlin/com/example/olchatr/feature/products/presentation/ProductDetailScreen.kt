package com.example.olchatr.feature.products.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.olchatr.core.components.LoadingScreen
import com.example.olchatr.feature.products.presentation.components.CircleIconButton
import com.example.olchatr.feature.products.presentation.components.QuantityButton

@Composable
fun ProductDetailScreen(
    product: Product?,
    onBack: () -> Unit = {},
    onFavoriteToggle: () -> Unit = {},
    isFavorite: Set<Int>,
) {
    var quantity by remember { mutableStateOf(1) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {
        when {
            product == null -> LoadingScreen()

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .navigationBarsPadding()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(420.dp)
                            .padding(vertical = 8.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(24.dp)
                            )
                    ) {
                        AsyncImage(
                            model = product.image,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp, vertical = 24.dp),
                            contentScale = ContentScale.Fit
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CircleIconButton(Icons.Default.ArrowBackIosNew, onClick = onBack)
                            CircleIconButton( if (isFavorite.contains(product.id)) Icons.Default.Favorite else Icons.Default.FavoriteBorder, onClick = onFavoriteToggle)
                        }
                    }

                    Spacer(Modifier.height(24.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = product.title,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(Modifier.height(8.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    Icons.Default.Star,
                                    contentDescription = null,
                                    tint = Color(0xFFFFC107),
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(Modifier.width(4.dp))
                                Text("${product.ratingRate}", fontWeight = FontWeight.Medium)
                                Spacer(Modifier.width(6.dp))
                                Text(
                                    "(${product.ratingCount} reviews)",
                                    fontSize = 14.sp,
                                    color = Color(0xFF347EFB)
                                )
                            }
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {

                            QuantityButton(Icons.Outlined.Remove) { if (quantity > 1) quantity-- }

                            Text(
                                quantity.toString(),
                                modifier = Modifier.padding(horizontal = 12.dp),
                                fontWeight = FontWeight.Bold
                            )

                            QuantityButton(Icons.Default.Add) { quantity++ }
                        }
                    }

                    Spacer(Modifier.height(14.dp))

                    Text(text = product.description, color = Color.Gray)

                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color.LightGray,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )

                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp),
                        shape = RoundedCornerShape(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2B2626)
                        ),
                    ) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = null)
                        Spacer(Modifier.width(10.dp))
                        Text(
                            text = "Add to Cart | $${product.price * quantity}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}