package com.example.lingai.ui.screens.lesson

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lingai.data.local.entity.LessonTopicEntity
import com.example.lingai.data.repository.LessonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonsViewModel @Inject constructor(
    private val repository: LessonRepository
) : ViewModel() {

    val topics = repository.getAllTopics()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun preloadTopics() {
        val sample = listOf(
            LessonTopicEntity(1, "Все слова A0", "📗", "A0", 100, 85, 10),
            LessonTopicEntity(2, "Все слова A1", "📘", "A1", 150, 120, 20),
            LessonTopicEntity(3, "Все слова A2", "📙", "A2", 200, 45, 25),
            LessonTopicEntity(4, "Все слова B1", "📒", "B1", 250, 0, 0),
            LessonTopicEntity(5, "Все слова B2", "📕", "B2", 300, 0, 0),
            LessonTopicEntity(6, "Все слова C1", "📔", "C1", 350, 0, 0),
            LessonTopicEntity(7, "Все слова C2", "📓", "C2", 400, 0, 0),
            LessonTopicEntity(8, "Базовые приветствия", "👋", "A1", 30, 30, 0),
            LessonTopicEntity(9, "Еда и напитки", "🍽️", "A1", 80, 60, 10),
            LessonTopicEntity(10, "Путешествия", "✈️", "A2", 70, 35, 20),
            LessonTopicEntity(11, "Работа и офис", "💼", "A2", 90, 0, 0),
            LessonTopicEntity(12, "Семья и друзья", "👨‍👩‍👧", "B1", 50, 40, 5),
            LessonTopicEntity(13, "Хобби и увлечения", "🎨", "B2", 60, 15, 10),
            LessonTopicEntity(14, "Погода и природа", "🌤️", "C1", 55, 25, 15),
            LessonTopicEntity(15, "Покупки", "🛍️", "C2", 65, 0, 0),
            LessonTopicEntity(16, "Здоровье", "💊", "C1", 75, 10, 10)
        )
        viewModelScope.launch { repository.insertTopics(sample) }
    }
}