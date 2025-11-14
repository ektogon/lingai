package com.example.lingai.ui.screens.learn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.lingai.ui.theme.Background

@Composable
fun LearnContent(
    state: LearnWordState,
    onAnswer: (Int) -> Unit,
    onSkip: () -> Unit,
    onContinue: () -> Unit
) {
    val q = state.currentQuestion ?: return

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {

        Column {

            LearnHeader(
                progress = state.progress,
                current = state.currentIndex + 1,
                total = state.total,
                isReviewMode = state.isReviewMode,
                onClose = onSkip // можно заменить на onClose если хочешь выход
            )

            QuestionContent(
                question = q,
                selectedAnswer = state.selectedAnswer,
                showResult = state.showResult,
                isCorrect = state.isCorrect,
                onSelect = onAnswer,
                onSkip = onSkip
            )
        }

        if (state.showResult) {
            ResultPanel(
                isCorrect = state.isCorrect,
                onNext = onContinue
            )
        }
    }
}
