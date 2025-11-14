package com.example.lingai.ui.navigation

import com.example.lingai.R

sealed class NavigationItem(val title: String, val icon : Int, val route: String)
{
    object Home : NavigationItem("Главная", R.drawable.ic_home,"home")
    object Lessons : NavigationItem("Темы",R.drawable.ic_book_open_text,"lessons")
    object Generated : NavigationItem("Генерация",R.drawable.ic_sparkles,"generated")
    object Exam :NavigationItem("Экзамен",R.drawable.ic_clipboard_check,"exam")
    object Profile : NavigationItem("Профиль",R.drawable.ic_user,"profile")
}