package com.example.lingai.ui.screens.learn

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun LearnWordScreen(
    state: LearnWordState,
    onEvent: (LearnWordEvent) -> Unit,
    onClose: () -> Unit
) {
    var showContent by remember { mutableStateOf(false) }
    LaunchedEffect(state.isLoading) {
        if (!state.isLoading) {
            delay(150)      // лёгкая задержка, чтобы анимация была гладкой
            showContent = true
        }
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {

        // --- Экран загрузки ---
        AnimatedVisibility(
            visible = !showContent,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        // --- Основное содержимое ---
        AnimatedVisibility(
            visible = showContent && !state.isLoading,
            enter = slideInVertically(initialOffsetY = { it / 3 }) + fadeIn(),
            exit = fadeOut()
        ) {
            LearnContent(
                state = state,
                onAnswer = { onEvent(LearnWordEvent.AnswerSelected(it)) },
                onSkip = { onEvent(LearnWordEvent.Skip) },
                onContinue = { onEvent(LearnWordEvent.Continue) },
                onClose = onClose
            )
        }
    }


    if (state.isFinished) {
        FinishedScreen(
            state = state,
            onRestartReview = { onEvent(LearnWordEvent.RestartReview) },
            onClose = onClose
        )
        return
    }
}