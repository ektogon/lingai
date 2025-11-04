package com.example.lingai.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(val title: String, val icon_ : ImageVector, val route: String) {
    object Home : NavigationItem("Главная",Icons.Default.Home,"home")
    object Lessons : NavigationItem("Темы",Icons.Filled.MenuBook,"lessons")
    object Generated : NavigationItem("Генерация",Icons.Default.Star,"generated")
    object Exam :NavigationItem("Экзамен",Icons.Filled.Assignment,"exam")
    object Profile : NavigationItem("Профиль",Icons.Default.Person,"profile")
}