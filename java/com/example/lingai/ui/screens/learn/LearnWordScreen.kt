package com.example.lingai.ui.screens.learn

import androidx.compose.runtime.Composable

@Composable
fun LearnWordScreen(
    state: LearnWordState,
    onEvent: (LearnWordEvent) -> Unit,
    onClose: () -> Unit
) {
    if (state.isFinished) {
        FinishedScreen(
            state = state,
            onRestartReview = { onEvent(LearnWordEvent.RestartReview) },
            onClose = onClose
        )
        return
    }

    LearnContent(
        state = state,
        onAnswer = { onEvent(LearnWordEvent.AnswerSelected(it)) },
        onSkip = { onEvent(LearnWordEvent.Skip) },
        onContinue = { onEvent(LearnWordEvent.Continue) }
    )
}
