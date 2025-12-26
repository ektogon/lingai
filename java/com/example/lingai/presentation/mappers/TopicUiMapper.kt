package com.example.lingai.presentation.mappers

import androidx.compose.ui.graphics.Color
import com.example.lingai.domain.models.LessonTopicModel
import com.example.lingai.presentation.theme.BluePrimary
import com.example.lingai.presentation.theme.GreenPrimary
import com.example.lingai.presentation.theme.OrangePrimary
import com.example.lingai.presentation.theme.RedPrimary
import com.example.lingai.presentation.ui_models.LessonTopicUi
import javax.inject.Inject

class TopicUiMapper @Inject constructor() {
    fun mapToUi(topics: List<LessonTopicModel>): List<LessonTopicUi> {
        return topics.mapIndexed { index, topic ->
            LessonTopicUi(
                topic = topic,
                progress = calculateProgress(topic),
                backgroundColor = resolveBackgroundColor(topic.level),
                rotation = calculateRotation(index),
                index = index
            )
        }
    }

    fun mapToUi(topic: LessonTopicModel): LessonTopicUi {
        return LessonTopicUi(
            topic = topic,
            progress = calculateProgress(topic),
            backgroundColor = resolveBackgroundColor(topic.level),
            rotation = calculateRotation(0),
            index = 0
        )
    }
}

private fun calculateProgress(topic: LessonTopicModel): Float {
    val total = topic.totalWords
    if (total <= 0) return 0f

    val completed = (topic.completedWords ?: 0).coerceIn(0, total)
    return (completed.toFloat() / total.toFloat()) * 100f
}

private fun resolveBackgroundColor(level: String): Color {
    return when (level) {
        "A0", "A1" -> GreenPrimary
        "A2" -> BluePrimary
        "B1", "B2" -> OrangePrimary
        "C1", "C2" -> RedPrimary
        else -> GreenPrimary
    }
}

private fun calculateRotation(index: Int): Float {
    return if (index % 2 == 0) 3f else -3f
}
