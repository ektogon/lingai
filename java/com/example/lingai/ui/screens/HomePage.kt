package com.example.lingai.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lingai.ui.components.ContentColumn
import com.example.lingai.ui.components.GradientButton
import com.example.lingai.ui.fragments.home.CustomThemeGenerator
import com.example.lingai.ui.fragments.home.DailyGoalProgress
import com.example.lingai.ui.fragments.home.PopularCategories
import com.example.lingai.ui.fragments.home.QuickStartCard
import com.example.lingai.ui.fragments.home.SeasonalThemesCarousel
import com.example.lingai.ui.theme.*

@Composable
fun HomePage() {
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
            colors = listOf(GreenPrimary, GreenDark)
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview
@Composable
fun HomePagePrew() {
    HomePage()
}