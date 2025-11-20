package com.example.lingai.ui.screens.learn

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LearnContent(
    state: LearnWordState,
    onAnswer: (Int) -> Unit,
    onSkip: () -> Unit,
    onContinue: () -> Unit,
    onClose: () -> Unit
) {
    val q = state.currentQuestion ?: return
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
    ) {
        LearnHeader(
            progress = state.progress,
            current = state.currentIndex + 1,
            total = state.total,
            isReviewMode = state.isReviewMode,
            onClose = onClose
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
        Box(modifier = Modifier.fillMaxSize()) {
            ResultPanel(
                modifier = Modifier.align(Alignment.BottomCenter),
                isCorrect = state.isCorrect,
                onNext = onContinue,
            )
        }
    }
}