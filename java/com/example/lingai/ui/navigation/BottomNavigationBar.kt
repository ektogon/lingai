package com.example.lingai.ui.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingai.ui.theme.Gray


@Composable
fun BottomNavigationBar(
    tabs: List<NavigationItem>,
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar(
        modifier = Modifier.shadow(
            8.dp,
            ambientColor = MaterialTheme.colorScheme.scrim,
            spotColor = MaterialTheme.colorScheme.scrim
        ),
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        tabs.forEach { tab ->
            val isActive = currentRoute == tab.route
            NavigationBarItem(
                selected = false,
                onClick = {
                    onNavigate(tab.route)
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(tab.icon),
                        contentDescription = tab.route,
                        tint = if (isActive) MaterialTheme.colorScheme.onSurface else Gray,
                        modifier = Modifier.size(20.dp)
                    )
                },
                label = {
                    Text(
                        text = tab.title,
                        fontFamily = MaterialTheme.typography.labelLarge.fontFamily,
                        fontSize = 11.sp,
                        color = if (isActive) MaterialTheme.colorScheme.onSurface else Gray,
                        fontWeight = if (isActive) FontWeight.Bold else FontWeight.SemiBold
                    )
                },
            )
        }
    }
}
