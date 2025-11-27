package com.example.lingai.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Brush

private val LightColorScheme = lightColorScheme(
    primary = GreenPrimary,  // Цвет основного элемента интерфейса, используется в: ExamPage - ExamModeOption, LessonPage - Icon, CustomThemeGenerator - Button
    onPrimary = White,  // Цвет текста на основных элементах, используется в: ExamPage - ExamModeOption
    primaryContainer = White,  // Цвет контейнера основных элементов, используется в: CustomThemeGenerator - Button
    onPrimaryContainer = GreenPrimary,  // Цвет текста на контейнере основных элементов, используется в: CustomThemeGenerator - Button

    secondary = White,  // Цвет второстепенных элементов, используется в: AdviceCard - Box, CardBlock - Card, ProgressBar - Box, ExamPage - Row
    onSecondary = TextPrimary,  // Цвет текста на второстепенных элементах, используется в: AdviceCard - Text, CategoryCard - Text, ExamPage - Text, DailyGoalProgress - Text
    secondaryContainer = GreenLight,  // Цвет контейнера второстепенных элементов, используется в: AdviceCard - Box, ProgressBar - Box, ExamPage - Row
    onSecondaryContainer = gray_600,  // Цвет текста на контейнере второстепенных элементов, используется в: AdviceCard - Text

    background = Background,  // Цвет фона приложения, используется в: MainActivity - Box, MainNavigation - NavHost, LearnWordScreen - Box, FinishedScreen - Box, ProfilePage - Box, TopicUiMapper - mapToUi
    onBackground = TextPrimary,  // Цвет текста на фоне, используется в: ExamPage - Text, HomePage - Text

    surface = White,  // Цвет поверхности карточек, используется в: BottomNavigationBar - NavigationBar, CustomThemeGenerator - Card
    onSurface = GreenPrimary,  // Цвет текста на поверхностях, используется в: BottomNavigationBar - NavigationBarItem, CustomThemeGenerator - Text

    scrim = Black  // Цвет затемнения фона, используется в: BottomNavigationBar - NavigationBar, CustomThemeGenerator - Card
)

// ----------------------
// 🎨 DARK THEME COLORS
// ----------------------


val DarkColorScheme = darkColorScheme(
    primary = Blue600,
    onPrimary = White,
    primaryContainer = DarkCharcoal,
    onPrimaryContainer = Blue600,

    secondary = DarkCharcoal,
    onSecondary = LightSilver,
    secondaryContainer = SoftBlue,
    onSecondaryContainer = gray_400,

    background = BackgroundDark,
    onBackground = LightSilver,

    surface = DarkCharcoal,
    onSurface = Blue600,

    scrim = White
)

private val LightGradients = Gradients(
    Brush.linearGradient(
        colors = listOf(
            GreenPrimary, GreenGradient
        ),
    )
)
private val DarkGradients = Gradients(
    Brush.linearGradient(
        colors = listOf(
            Blue600, Blue700
        ),
    )
)

@Composable
fun LingaiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val gradients: Gradients = when {
        darkTheme -> DarkGradients
        else -> LightGradients
    }

    CompositionLocalProvider(
        ThemeGradients provides gradients
    ) {
        MaterialTheme(
            colorScheme = colorScheme, typography = Typography, content = content
        )
    }
}

