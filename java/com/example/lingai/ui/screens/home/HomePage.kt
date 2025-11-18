package com.example.lingai.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lingai.ui.components.ContentColumn
import com.example.lingai.ui.components.GradientButton
import com.example.lingai.ui.theme.OrangeLight
import com.example.lingai.ui.theme.OrangePrimary

@Composable
fun HomePage(navController: NavController) {

    ContentColumn(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        //Генератор тем
        CustomThemeGenerator()
        Spacer(modifier = Modifier.height(24.dp))

        //Дневная цель
        DailyGoalProgress()
        Spacer(modifier = Modifier.height(24.dp))

        //Сезонные темы
        Text(
            text = "Сезонные темы",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(modifier = Modifier.height(16.dp))

        SeasonalThemesCarousel()
        Spacer(modifier = Modifier.height(24.dp))

        //Популярные темы
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Популярные темы",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Icon(
                Icons.Default.Star,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier.size(18.dp)
            )
        }
        PopularCategories()
        Spacer(modifier = Modifier.height(24.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Начни сегодня",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(OrangeLight)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        Icons.Default.Bolt,
                        contentDescription = null,
                        tint = OrangePrimary,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }
        Spacer(Modifier.padding(0.dp,10.dp,0.dp,))
        QuickStartCard()
        Spacer(modifier = Modifier.height(24.dp))
        GradientButton(
            text = "Все мои темы →",
            onClick = { navController.navigate("themes") },
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