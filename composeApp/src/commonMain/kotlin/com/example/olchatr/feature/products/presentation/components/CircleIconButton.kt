package com.example.olchatr.feature.products.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
internal fun CircleIconButton(icon: ImageVector, onClick: () -> Unit = {}) {

    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(42.dp)
            .shadow(
                elevation = 8.dp,
                shape = CircleShape,
                clip = false
            )
            .background(
                color = Color.White,
                CircleShape
            )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}