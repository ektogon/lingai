package com.example.lingai.domain.models

data class QuestionModel(
    val correctAnswer: Word,
    val variants: List<Word>,
    val correctIndex: Int
)