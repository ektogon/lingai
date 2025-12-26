package com.example.lingai.presentation.screens.learn

import com.example.lingai.domain.models.QuestionModel
import com.example.lingai.domain.models.WrongAnswerModel

data class LearnWordState(
    val isLoading: Boolean = false,
    val questions: List<QuestionModel> = emptyList(),
    val currentIndex: Int = 0,
    val selectedAnswer: Int? = null,
    val showResult: Boolean = false,
    val isCorrect: Boolean = false,
    val wrongAnswers: List<WrongAnswerModel> = emptyList(),
    val isReviewMode: Boolean = false,
    val isFinished: Boolean = false
) {
    val firstWord = questions.firstOrNull()
    val currentWord get() = if (firstWord != null) questions[currentIndex].correctAnswer
        else null
    val total get() = if (isReviewMode) wrongAnswers.size else questions.size
    val progress get() = if (total == 0) 0f else currentIndex.toFloat() / total.toFloat()
    val currentQuestion: QuestionModel?
        get() = if (isReviewMode)
            wrongAnswers.getOrNull(currentIndex)?.question
        else
            questions.getOrNull(currentIndex)
}
