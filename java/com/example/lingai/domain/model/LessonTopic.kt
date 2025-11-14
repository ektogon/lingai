package com.example.lingai.domain.model

data class LessonTopic(
    val id: Int,
    val title: String,
    val emoji: String,
    val level: String,
    val totalWords: Int,
    val completedWords: Int,
    val learningWords: Int,
    val words: List<Word>
)