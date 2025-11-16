package com.example.lingai.ui.screens.lesson

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lingai.data.repository.LessonRepository
import com.example.lingai.domain.model.LessonTopic
import com.example.lingai.ui.mappers.TopicUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonsViewModel @Inject constructor(
    private val repository: LessonRepository,
    private val uiMapper: TopicUiMapper,
) : ViewModel() {

    val topics = repository.getTopicWithWords()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    val topicsUi = topics.map{uiMapper.mapToUi(it)}.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    // Выбранная тема
    private val _selectedTopic = MutableStateFlow<LessonTopic?>(null)
    val selectedTopic = _selectedTopic.asStateFlow()

    // Навигационные события (одноразовые)
    private val _navigateToLearn = MutableSharedFlow<Int>()
    val navigateToLearn = _navigateToLearn.asSharedFlow()

    /** Пользователь нажал на тему */
    fun onTopicClicked(topic: LessonTopic) {
        _selectedTopic.value = topic
    }

    /** Закрыть диалог */
    fun closeDialog() {
        _selectedTopic.value = null
    }

    /** Начать обучение (внутри ViewModel формируем JSON + отправляем событие) */
    fun startLearning() {
        val topic = _selectedTopic.value ?: return

        viewModelScope.launch {
            delay(150)
            _navigateToLearn.emit(topic.id)
        }

        _selectedTopic.value = null
    }
}