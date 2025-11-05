package com.example.lingai.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

import androidx.compose.ui.graphics.Color

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    gradient: Brush? = null,
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(20.dp)
    Surface(
        modifier = modifier.shadow(8.dp, shape = shape),
        shape = shape,
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .background(
                    brush = gradient ?: Brush.linearGradient(listOf(backgroundColor, backgroundColor)),
                    shape = shape
                )
                .padding(16.dp)
        ) {
            content()
        }
    }
}
