package com.example.lingai.data.model

data class Question(
    val correctAnswer: Word,
    val variants: List<Word>,
    val correctIndex: Int
)