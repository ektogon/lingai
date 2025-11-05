package com.example.lingai.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.lingai.R

sealed class NavigationItem(val title: String, val icon : Int, val route: String)
{
    object Home : NavigationItem("Главная", R.drawable.ic_home,"home")
    object Lessons : NavigationItem("Темы",R.drawable.book_open_text,"lessons")
    object Generated : NavigationItem("Генерация",R.drawable.sparkles,"generated")
    object Exam :NavigationItem("Экзамен",R.drawable.clipboard_check,"exam")
    object Profile : NavigationItem("Профиль",R.drawable.user,"profile")
}