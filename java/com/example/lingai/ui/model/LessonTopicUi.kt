package com.example.lingai.ui.model

import androidx.compose.ui.graphics.Color
import com.example.lingai.domain.model.LessonTopic

data class LessonTopicUi(
    val topic: LessonTopic,
    val progress: Float,
    val backgroundColor: Color,
    val rotation: Float,
    val index: Int
)