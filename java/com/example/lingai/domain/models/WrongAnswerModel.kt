package com.example.lingai.domain.models

data class WrongAnswerModel(
    val question: QuestionModel,
    val selectedIndex: Int
)