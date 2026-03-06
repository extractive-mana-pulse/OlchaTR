package com.example.olchatr.feature.products.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomFloatingBar(
    modifier: Modifier = Modifier,
    selectedIndex: Int = 0,
    onItemClick: (Int) -> Unit = {}
) {
    val icons = listOf(
        Pair(Icons.Outlined.Home, Icons.Filled.Home),
        Pair(Icons.Outlined.FavoriteBorder, Icons.Filled.Favorite),
        Pair(Icons.Outlined.ShoppingCart, Icons.Filled.ShoppingCart),
        Pair(Icons.Outlined.Person, Icons.Filled.Person)
    )

    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(
                color = Color(0xFF292526),
                shape = RoundedCornerShape(48.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        icons.forEachIndexed { index, (outlined, filled) ->
            val isSelected = selectedIndex == index

            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(
                        color = Color(0x0DFFFFFF),
                        shape = CircleShape
                    )
                    .clickable { onItemClick(index) }
                    .padding(8.dp)
                ,
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (isSelected) filled else outlined,
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        }
    }
}