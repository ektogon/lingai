package com.example.lingai.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lingai.ui.components.ContentColumn
import com.example.lingai.ui.components.GradientButton
import com.example.lingai.ui.components.SeasonalTheme
import com.example.lingai.ui.theme.Background
import com.example.lingai.ui.theme.GreenDark
import com.example.lingai.ui.theme.GreenPrimary

@Composable
fun HomePage(navController: NavController) {
    val seasonalThemes = listOf(
        SeasonalTheme(
            1,
            "Осень",
            "https://images.unsplash.com/photo-1636327648402-fa5297a87efc?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=400",
            24,
            65
        ),
        SeasonalTheme(
            2,
            "Хэллоуин",
            "https://images.unsplash.com/photo-1667223687781-b6025c7cd48d?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=400",
            18,
            35
        ),
        SeasonalTheme(
            3,
            "Новый год",
            "https://images.unsplash.com/photo-1704399527621-82de0422490c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=400",
            32,
            80
        ),
        SeasonalTheme(
            4,
            "Зима",
            "https://images.unsplash.com/photo-1542609715982-16f4e1e2cfc0?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=400",
            28,
            50
        )
    )
    Column(Modifier
        .fillMaxSize()
        .background(Background)) {
        ContentColumn(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            CustomThemeGenerator()
            Spacer(modifier = Modifier.height(24.dp))
            DailyGoalProgress()
            Spacer(modifier = Modifier.height(24.dp))
            SeasonalThemesCarousel()
            Spacer(modifier = Modifier.height(24.dp))
            PopularCategories()
            Spacer(modifier = Modifier.height(24.dp))
            QuickStartCard()
            Spacer(modifier = Modifier.height(24.dp))
            GradientButton(
                text = "Все мои темы →",
                colors = listOf(GreenPrimary, GreenDark),
                onClick = { navController.navigate("themes") },
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }

}

@Preview
@Composable
fun HomePagePrew() {
    val navController = rememberNavController()
    HomePage(navController = navController)
}