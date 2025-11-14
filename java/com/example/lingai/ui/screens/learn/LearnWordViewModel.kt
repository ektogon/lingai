package com.example.lingai.ui.screens.learn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lingai.data.repository.LessonRepository
import com.example.lingai.domain.model.WrongAnswer
import com.example.lingai.domain.usecase.GenerateQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LearnWordViewModel @Inject constructor(
    private val repository: LessonRepository,
    private val generateQuestions: GenerateQuestionsUseCase
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
            is LearnWordEvent.AnswerSelected -> answer(event.index)
            LearnWordEvent.Continue -> next()
            LearnWordEvent.Skip -> skip()
            LearnWordEvent.RestartReview -> restartReview()
            LearnWordEvent.Finish -> finish()
        }
    }

    private fun answer(index: Int) {
        val s = _state.value
        val q = s.currentQuestion ?: return
        val correct = index == q.correctIndex

        val wrong = if (!correct && !s.isReviewMode)
            s.wrongAnswers + WrongAnswer(q, index)
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
            s.wrongAnswers + WrongAnswer(q, -1)
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
}
