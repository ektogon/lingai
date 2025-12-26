package com.example.lingai.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lingai.domain.models.LessonTopicModel
import com.example.lingai.domain.repository.LessonRepository
import com.example.lingai.presentation.mappers.TopicUiMapper
import com.example.lingai.presentation.ui_models.LessonTopicUi
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
        .stateIn(viewModelScope, SharingStarted.Companion.Lazily, emptyList())

    val topicsUi = topics.map {uiMapper.mapToUi(it)}.stateIn(viewModelScope, SharingStarted.Companion.Lazily, emptyList())
    // Выбранная тема
    private val _selectedTopic = MutableStateFlow<LessonTopicModel?>(null)
    val selectedTopic = _selectedTopic.asStateFlow()

    // Навигационные события (одноразовые)
    private val _navigateToLearn = MutableSharedFlow<Int>()
    val navigateToLearn = _navigateToLearn.asSharedFlow()

    /** Пользователь нажал на тему */
    fun onTopicClicked(topic: LessonTopicModel) {
        _selectedTopic.value = topic
    }

    fun getTopicUi(topic: LessonTopicModel):LessonTopicUi{
        return uiMapper.mapToUi(topic)
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