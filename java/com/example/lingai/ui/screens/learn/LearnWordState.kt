package com.example.lingai.ui.screens.learn

import com.example.lingai.domain.model.Question
import com.example.lingai.domain.model.WrongAnswer

data class LearnWordState(
    val isLoading: Boolean = false,
    val questions: List<Question> = emptyList(),
    val currentIndex: Int = 0,
    val selectedAnswer: Int? = null,
    val showResult: Boolean = false,
    val isCorrect: Boolean = false,
    val wrongAnswers: List<WrongAnswer> = emptyList(),
    val isReviewMode: Boolean = false,
    val isFinished: Boolean = false
) {
    val total get() = if (isReviewMode) wrongAnswers.size else questions.size
    val progress get() = if (total == 0) 0f else currentIndex.toFloat() / total.toFloat()
    val currentQuestion: Question?
        get() = if (isReviewMode)
            wrongAnswers.getOrNull(currentIndex)?.question
        else
            questions.getOrNull(currentIndex)
}
