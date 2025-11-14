package com.example.lingai.ui.screens.learn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.lingai.domain.model.Question
import com.example.lingai.domain.model.WrongAnswer
import com.example.lingai.ui.theme.*

@Composable
fun LearnWordScreen(
    questions: List<Question>,
    onClose: () -> Unit
) {
    var currentIndex by remember { mutableIntStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<Int?>(null) }
    var showResult by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf(false) }
    var wrongAnswers by remember { mutableStateOf<List<WrongAnswer>>(emptyList()) }
    var isReviewMode by remember { mutableStateOf(false) }
    var reviewQuestions by remember { mutableStateOf<List<Question>>(emptyList()) }

    val currentList = if (isReviewMode) reviewQuestions else questions
    val totalQuestions = currentList.size
    val isComplete = currentIndex >= totalQuestions

    if (isComplete) {
        LearnSummaryScreen(
            wrongAnswers = wrongAnswers,
            isReviewMode = isReviewMode,
            totalCount = totalQuestions,
            onRetry = {
                reviewQuestions = wrongAnswers.map { it.question }
                wrongAnswers = emptyList()
                isReviewMode = true
                currentIndex = 0
            },
            onFinish = {
                wrongAnswers = emptyList()
                isReviewMode = false
                reviewQuestions = emptyList()
                currentIndex = 0
                onClose()
            }
        )
        return
    }

    val currentQuestion = currentList[currentIndex]
    val progress = (currentIndex.toFloat() / totalQuestions.toFloat())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Column {
            LearnHeader(
                progress = progress,
                current = currentIndex + 1,
                total = totalQuestions,
                isReviewMode = isReviewMode,
                onClose = onClose
            )

            QuestionContent(
                question = currentQuestion,
                selectedAnswer = selectedAnswer,
                showResult = showResult,
                isCorrect = isCorrect,
                onSelect = { index ->
                    if (selectedAnswer == null) {
                        selectedAnswer = index
                        isCorrect = index == currentQuestion.correctIndex
                        showResult = true

                        if (!isCorrect && !isReviewMode) {
                            wrongAnswers = wrongAnswers + WrongAnswer(currentQuestion, index)
                        }
                    }
                },
                onSkip = {
                    if (!isReviewMode) {
                        wrongAnswers = wrongAnswers + WrongAnswer(currentQuestion, -1)
                    }
                    currentIndex++
                }
            )
        }

        if (showResult) {
            ResultPanel(
                isCorrect = isCorrect,
                onNext = {
                    selectedAnswer = null
                    showResult = false
                    currentIndex++
                }
            )
        }
    }
}
