package com.example.lingai.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = GreenPrimary,  // Цвет основного элемента интерфейса, используется в: ExamPage - ExamModeOption, LessonPage - Icon, CustomThemeGenerator - Button
    onPrimary = White,  // Цвет текста на основных элементах, используется в: ExamPage - ExamModeOption
    primaryContainer = White,  // Цвет контейнера основных элементов, используется в: CustomThemeGenerator - Button
    onPrimaryContainer = GreenPrimary,  // Цвет текста на контейнере основных элементов, используется в: CustomThemeGenerator - Button

    secondary = White,  // Цвет второстепенных элементов, используется в: AdviceCard - Box, CardBlock - Card, ProgressBar - Box, ExamPage - Row
    onSecondary = TextPrimary,  // Цвет текста на второстепенных элементах, используется в: AdviceCard - Text, CategoryCard - Text, ExamPage - Text, DailyGoalProgress - Text
    secondaryContainer = GreenLight,  // Цвет контейнера второстепенных элементов, используется в: AdviceCard - Box, ProgressBar - Box, ExamPage - Row
    onSecondaryContainer = TextSecondary,  // Цвет текста на контейнере второстепенных элементов, используется в: AdviceCard - Text

    onTertiaryContainer = Gold,  // Цвет текста на терциарном контейнере, используется в: HomePage - Text, ExamPage - Text, LearnHeader - Text

    background = Background,  // Цвет фона приложения, используется в: MainActivity - Box, MainNavigation - NavHost, LearnWordScreen - Box, FinishedScreen - Box, ProfilePage - Box, TopicUiMapper - mapToUi
    onBackground = TextPrimary,  // Цвет текста на фоне, используется в: ExamPage - Text, HomePage - Text


    surface = White,  // Цвет поверхности карточек, используется в: BottomNavigationBar - NavigationBar, CustomThemeGenerator - Card
    onSurface = GreenPrimary,  // Цвет текста на поверхностях, используется в: BottomNavigationBar - NavigationBarItem, CustomThemeGenerator - Text

    outline = Color(0xFF8C8C8C).copy(alpha = 0.1f),  // Цвет обводки элементов, используется в: BottomNavigationBar - NavigationBarItem

    error = RedPrimary,  // Цвет ошибки, используется в: Theme
    onError = White,  // Цвет текста на элементах ошибки, используется в: Theme
    scrim = Color(0xFF000000)  // Цвет затемнения фона, используется в: BottomNavigationBar - NavigationBar, CustomThemeGenerator - Card
)

// ----------------------
// 🎨 DARK THEME COLORS
// ----------------------


val DarkColorScheme = darkColorScheme(
    primary = Blue600,  // Цвет основного элемента интерфейса, используется в: ExamPage - ExamModeOption (RadioButton), ExamPage - LinguaSlider (accentColor)
    onPrimary = White,  // Цвет текста на основных элементах, используется в: ExamPage - ExamModeOption (Text)
    primaryContainer = Color(0xFF2B2B2B),  // Цвет контейнера основных элементов, используется в: Theme
    onPrimaryContainer = Color(0xFFD8E2FF),  // Цвет текста на контейнере основных элементов, используется в: Theme

    secondary = Color(0xFF2B2B2B),  // Цвет второстепенных элементов, используется в: AdviceCard - Card, CardBlock - Card, ProgressBar - Background, ExamPage - Row (ExamModeOption border)
    onSecondary = Color(0xFFE5E5E5),  // Цвет текста на второстепенных элементах, используется в: AdviceCard - Text, CategoryCard - Text, ExamPage - Text, DailyGoalProgress - Text
    secondaryContainer = Color(0xFFB8C4EA),  // Цвет контейнера второстепенных элементов, используется в: AdviceCard - Card, ProgressBar - Background, ExamPage - Row (ExamModeOption border)
    onSecondaryContainer = TextPrimary,  // Цвет текста на контейнере второстепенных элементов, используется в: AdviceCard - Text

    tertiary = Color(0xFFF0B6C8),  // Цвет терциарного элемента, используется в: Theme
    onTertiary = Color(0xFF492535),  // Цвет текста на терциарных элементах, используется в: Theme
    tertiaryContainer = Color(0xFF633B4B),  // Цвет контейнера терциарных элементов, используется в: Theme
    onTertiaryContainer = Gold,  // Цвет текста на контейнере терциарных элементов, используется в: HomePage - Icon (Star)

    background = Color(0xFF1F1F1F),  // Цвет фона приложения, используется в: MainActivity - Box, MainNavigation - NavHost, ExamPage - ContentColumn, HomePage - ContentColumn
    onBackground = Color(0xFFE5E5E5),  // Цвет текста на фоне, используется в: ExamPage - Text (headlineLarge), HomePage - Text (titleLarge), LessonPage - Text (titleLarge)

    surface = Color(0xFF2B2B2B),  // Цвет поверхности карточек, используется в: BottomNavigationBar - NavigationBar
    onSurface = Blue600,  // Цвет текста на поверхностях, используется в: BottomNavigationBar - NavigationBarItem (inactive icon and text)
    surfaceVariant = Color(0xFF1E1E1E),  // Цвет варианта поверхности, используется в: Theme
    onSurfaceVariant = Color(0xFFC2C2C2),  // Цвет текста на варианте поверхности, используется в: Theme

    outline = Color(0xFF8C8C8C).copy(alpha = 0.1f),  // Цвет обводки элементов, используется в: Theme
    outlineVariant = Color(0xFF404040),  // Цвет варианта обводки, используется в: Theme

    error = Color(0xFFCF6679),  // Цвет ошибки, используется в: Theme
    onError = Color(0xFF680019),  // Цвет текста на элементах ошибки, используется в: Theme
    errorContainer = Color(0xFF93002A),  // Цвет контейнера ошибки, используется в: Theme
    onErrorContainer = Color(0xFFFFD9DE),  // Цвет текста на контейнере ошибки, используется в: Theme

    inverseSurface = Color(0xFFE0E0E0),  // Цвет инвертированной поверхности, используется в: Theme
    inverseOnSurface = Color(0xFF2D2D2D),  // Цвет текста на инвертированной поверхности, используется в: Theme
    inversePrimary = Color(0xFF3A5A9A),  // Цвет инвертированного основного элемента, используется в: Theme

    scrim = Color(0xFFffffff)  // Цвет затемнения фона, используется в: BottomNavigationBar - NavigationBar (shadow)
)

private val LightGradients = Gradients(
    Brush.linearGradient(
        colors = listOf(
            GreenBasic, GreenSecondary
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
    darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit
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

