package com.example.lingai.ui.screens.home

import androidx.compose.foundation.layout.Spacer
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
import com.example.lingai.ui.theme.GreenDark
import com.example.lingai.ui.theme.GreenPrimary

@Composable
fun HomePage(navController: NavController,) {
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
            onClick = {navController.navigate("themes")},
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview
@Composable
fun HomePagePrew() {
    val navController = rememberNavController()
    HomePage(navController = navController)
}