package com.example.lingai.presentation.ui_models

import androidx.compose.ui.graphics.Color
import com.example.lingai.domain.models.LessonTopicModel

data class LessonTopicUi(
    val topic: LessonTopicModel,
    val progress: Float,
    val backgroundColor: Color,
    val rotation: Float,
    val index: Int
)