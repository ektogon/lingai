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
}