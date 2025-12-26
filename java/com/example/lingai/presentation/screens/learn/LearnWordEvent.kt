package com.example.lingai.presentation.screens.learn

sealed interface LearnWordEvent  {
    data object Reset : LearnWordEvent
    data class AnswerSelected(val index: Int) : LearnWordEvent
    object Continue : LearnWordEvent
    object Skip : LearnWordEvent
    object Finish : LearnWordEvent
    object RestartReview : LearnWordEvent
    object PlayAudio : LearnWordEvent
}
