package com.example.lingai.domain.models

data class QuestionModel(
    val correctAnswer: WordModel,
    val variants: List<WordModel>,
    val correctIndex: Int
)