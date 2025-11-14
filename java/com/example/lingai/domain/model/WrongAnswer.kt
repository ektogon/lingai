package com.example.lingai.domain.model

data class WrongAnswer(
    val question: Question,
    val selectedIndex: Int
)