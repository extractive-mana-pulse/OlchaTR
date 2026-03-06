package com.example.olchatr.feature.products.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CategorySection() {

    val categories = listOf(
        "All Items",
        "men's clothing",
        "jewelery",
        "women's clothing",
        "electronics"
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(categories.size) { index ->

            val selected = index == 0

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        color = if (selected) Color.Black else Color.White
                    )
                    .border(
                        width = 1.dp,
                        color = if(selected) Color.Transparent else Color.LightGray,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {

                Text(
                    text = categories[index],
                    color = if (selected) Color.White else Color.Black
                )
            }
        }
    }
}