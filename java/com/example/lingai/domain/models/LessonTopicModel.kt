package com.example.lingai.domain.models

enum class TopicStatus { NEW, IN_PROGRESS, LEARNED }
data class LessonTopicModel(
    val id: Int,
    val title: String,
    val translation: String,
    val emoji: String,
    val level: String,
    val status: String? = "NEW",
    val learningWords: Int? = 0,
    val completedWords: Int? = 0,
    val totalWords: Int,
    val words: List<WordModel>
)