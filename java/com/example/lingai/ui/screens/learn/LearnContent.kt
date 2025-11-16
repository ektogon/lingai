package com.example.lingai.ui.screens.learn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lingai.domain.model.Question
import com.example.lingai.domain.model.Word
import com.example.lingai.domain.model.WordStatus
import com.example.lingai.ui.theme.Background

@Composable
fun LearnContent(
    state: LearnWordState,
    onAnswer: (Int) -> Unit,
    onSkip: () -> Unit,
    onContinue: () -> Unit,
    onClose: () -> Unit
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
                onClose = onClose // можно заменить на onClose если хочешь выход
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
                onNext = onContinue,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LearnContentPreview() {
    // ---------- ФЕЙКОВЫЕ ДАННЫЕ ----------
    val fakeQuestion = Question(
        correctAnswer = Word("apple", "яблоко",WordStatus.NEW),
        variants = listOf(
            Word("apple", "яблоко", WordStatus.NEW),
            Word("table", "стол",WordStatus.NEW),
            Word("cat", "кот",WordStatus.NEW),
            Word("sun", "солнце",WordStatus.NEW)
        ),
        correctIndex = 0
    )

    val fakeState = LearnWordState(
        isLoading = true,
        isFinished = false,
        currentIndex = 0,
        questions = listOf(fakeQuestion),
        wrongAnswers = emptyList(),
        selectedAnswer = null,
        showResult = false,
        isCorrect = false,
        isReviewMode = false
    )

    LearnContent(
        state = fakeState,
        onAnswer = {},
        onSkip = {},
        onContinue = {},
        onClose = {}
    )
}