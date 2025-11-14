package com.example.lingai.ui.mappers

import androidx.compose.ui.graphics.Color
import com.example.lingai.domain.model.LessonTopic
import com.example.lingai.ui.model.LessonTopicUi
import com.example.lingai.ui.theme.BluePrimary
import com.example.lingai.ui.theme.GreenPrimary
import com.example.lingai.ui.theme.OrangePrimary
import com.example.lingai.ui.theme.RedPrimary
import javax.inject.Inject

class TopicUiMapper @Inject constructor(){
    fun mapToUi(topics: List<LessonTopic>): List<LessonTopicUi> {
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
    private fun calculateProgress(topic: LessonTopic): Float {
        if (topic.totalWords == 0) return 0f
        return (topic.completedWords.toFloat() / topic.totalWords.toFloat()) * 100f
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
}