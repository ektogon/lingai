package com.example.lingai.domain.models

data class LessonTopicModel(
    val id: Int,
    val title: String,
    val translation: String,
    val emoji: String,
    val level: String,
    val totalWords: Int,
    val completedWords: Int,
    val learningWords: Int,
    val words: List<Word>
)