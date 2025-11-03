package com.example.lingai.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class NavigationItem(
    val id: String,
    val label: String,
    val icon: ImageVector
)

object DuolingoNavColors {
    val GreenPrimary = Color(0xFF6B9E78)
    val Gray = Color(0xFF9E9E9E)
    val White = Color.White
    val Border = Color(0xFFE0E0E0)
}

@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        tabs.forEach { tab ->
            val isActive = currentRoute == tab.id
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onNavigate(tab.id) }
                    .padding(vertical = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = tab.icon,
                    contentDescription = tab.label,
                    tint = if (isActive) DuolingoNavColors.GreenPrimary else DuolingoNavColors.Gray,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = tab.label,
                    fontSize = 9.sp,
                    color = if (isActive) DuolingoNavColors.GreenPrimary else DuolingoNavColors.Gray,
                    fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Normal
                )
            }
        }
    }
}

val tabs = listOf(
    NavigationItem("home", "Главная", Icons.Default.Home),
    NavigationItem("lessons", "Темы", Icons.Default.Star),
    NavigationItem("generated", "Генерация", Icons.Default.Star),
    NavigationItem("exam", "Экзамен", Icons.Default.Star),
    NavigationItem("profile", "Профиль", Icons.Default.Person)
)