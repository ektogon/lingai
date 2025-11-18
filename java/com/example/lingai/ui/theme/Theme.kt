 package com.example.lingai.ui.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

 private val LightColorScheme = lightColorScheme(
     background = Color(0xFFF7FDF9),         // --background
     onBackground = Color(0xFF3C3C3C),       // --foreground

     primary = Color(0xFF030213),            // --primary
     onPrimary = Color.White,                // --primary-foreground

     secondary = Color(0xFFEFF0FE),          // --secondary
     onSecondary = Color(0xFF030213),

     surface = Color.White,                  // --card
     onSurface = Color(0xFF3C3C3C),          // --card-foreground

     error = Color(0xFFD4183D),              // --destructive
     onError = Color.White
 )

// ----------------------
// 🎨 DARK THEME COLORS
// ----------------------

 private val DarkColorScheme = darkColorScheme(
     background = Color(0xFF1F1F1F),         // --background
     onBackground = Color(0xFFE5E5E5),

     primary = Color(0xFFE5E5E5),            // --primary
     onPrimary = Color(0xFF1F1F1F),

     secondary = Color(0xFF3A3A3A),
     onSecondary = Color(0xFFE5E5E5),

     surface = Color(0xFF2B2B2B),            // --card
     onSurface = Color(0xFFE5E5E5),

     error = Color(0xFFDC2626),
     onError = Color.White
 )


@Composable
fun LingaiTheme(
    darkTheme: Boolean = false,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}