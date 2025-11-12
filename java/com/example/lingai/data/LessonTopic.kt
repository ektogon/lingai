package com.example.lingai.data

import com.example.lingai.data.WordItem

data class LessonTopic(
    val id: String,
    val title: String,
    val emoji: String,
    val level: String,
    val totalWords: Int,
    val completedWords: Int,
    val learningWords: Int,
    val words: List<WordItem>
)