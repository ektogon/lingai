package com.example.lingai.ui.screens.learn

sealed interface LearnWordEvent {
    data class AnswerSelected(val index: Int) : LearnWordEvent
    object Continue : LearnWordEvent
    object Skip : LearnWordEvent
    object Finish : LearnWordEvent
    object RestartReview : LearnWordEvent
}
