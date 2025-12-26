package com.example.lingai.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lingai.domain.models.WrongAnswerModel
import com.example.lingai.domain.repository.LessonRepository
import com.example.lingai.domain.usecase.GenerateQuestionsUseCase
import com.example.lingai.presentation.screens.learn.LearnWordEvent
import com.example.lingai.presentation.screens.learn.LearnWordState
import com.example.lingai.tts.TtsSpeaker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LearnWordViewModel @Inject constructor(
    private val repository: LessonRepository,
    private val generateQuestions: GenerateQuestionsUseCase,
    private val ttsSpeaker: TtsSpeaker,
) : ViewModel() {

    private val _state = MutableStateFlow(LearnWordState())
    val state = _state.asStateFlow()

    fun loadTopic(topicId: Int) {
        viewModelScope.launch {
            val topic = repository.getTopicWithWordsById(topicId)
            val questions = generateQuestions.invoke(topic.words)

            _state.value = LearnWordState(questions = questions)
        }
    }

    fun onEvent(event: LearnWordEvent) {
        when (event) {
            is LearnWordEvent.Reset -> reset()
            is LearnWordEvent.AnswerSelected -> answer(event.index)
            LearnWordEvent.Continue -> next()
            LearnWordEvent.Skip -> skip()
            LearnWordEvent.RestartReview -> restartReview()
            LearnWordEvent.Finish -> finish()
            LearnWordEvent.PlayAudio -> onPlayWord()
        }
    }

    fun reset() {
        _state.value = LearnWordState(
            isLoading = true,
        )
        _state.value = LearnWordState(
            isLoading = false,
        )
    }

    private fun answer(index: Int) {
        val s = _state.value
        val q = s.currentQuestion ?: return
        val correct = index == q.correctIndex

        val wrong = if (!correct && !s.isReviewMode)
            s.wrongAnswers + WrongAnswerModel(q, index)
        else s.wrongAnswers

        _state.value = s.copy(
            selectedAnswer = index,
            showResult = true,
            isCorrect = correct,
            wrongAnswers = wrong
        )
    }

    private fun skip() {
        val s = _state.value
        val q = s.currentQuestion ?: return

        val wrong = if (!s.isReviewMode)
            s.wrongAnswers + WrongAnswerModel(q, -1)
        else s.wrongAnswers

        _state.value = s.copy(
            wrongAnswers = wrong,
            currentIndex = s.currentIndex + 1,
            selectedAnswer = null,
            showResult = false
        ).finishCheck()
    }

    private fun next() {
        val s = _state.value
        _state.value = s.copy(
            currentIndex = s.currentIndex + 1,
            selectedAnswer = null,
            showResult = false
        ).finishCheck()
    }

    private fun restartReview() {
        _state.value = _state.value.copy(
            isReviewMode = true,
            currentIndex = 0,
            showResult = false,
            selectedAnswer = null
        )
    }

    private fun finish() {
        _state.value = _state.value.copy(isFinished = true)
    }

    private fun LearnWordState.finishCheck(): LearnWordState {
        return if (currentIndex >= total)
            this.copy(isFinished = true)
        else this
    }

    fun onPlayWord() {
        val word = _state.value.currentWord
        Log.d("1", "onPlayWord: $word")
        if (word == null) return
        val textToSpeak = word.original.trim()
        if (textToSpeak.isEmpty()) return

        ttsSpeaker.speak(textToSpeak)
    }
}