package com.example.lingai.presentation.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush

data class Gradients(
    val primaryGradient: Brush,
//    val secondaryGradient: Brush
)

val ThemeGradients = staticCompositionLocalOf<Gradients> {
    error("No gradients provided")
}