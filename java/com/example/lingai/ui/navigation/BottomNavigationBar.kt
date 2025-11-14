package com.example.lingai.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingai.ui.theme.*


@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    tabs: List<NavigationItem>,
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar(
        modifier = modifier,
        containerColor = White) {
        tabs.forEach { tab ->
            val isActive = currentRoute == tab.route
            NavigationBarItem(
                selected = isActive,
                onClick = {
                    onNavigate(tab.route)
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(tab.icon),
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
