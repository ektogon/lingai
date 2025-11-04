package com.example.lingai.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingai.ui.theme.*


@Composable
fun BottomNavigationBar(
    tabs: List<NavigationItem>,
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar(Modifier.background(Background)) {
        tabs.forEach { tab ->
            val isActive = currentRoute == tab.route
            NavigationBarItem(
                selected = isActive,
                onClick = {
                    onNavigate(tab.route)
                },
                icon = {
                    Icon(
                        imageVector = tab.icon_,
                        contentDescription = tab.route,
                        tint = if (isActive) GreenPrimary else Gray,
                        modifier = Modifier.size(20.dp)
                    )
                },
                label = {
                    Text(
                        text = tab.title,
                        fontSize = 9.sp,
                        color = if (isActive) GreenPrimary else Gray,
                        fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Normal
                    )
                },
            )
        }
    }
}
