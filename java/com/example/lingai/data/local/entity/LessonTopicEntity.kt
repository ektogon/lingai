package com.example.lingai.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "lesson_topic",
    indices = [
        Index("level"),
        Index("title")
    ]
)
data class LessonTopicEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val translation: String,
    val emoji: String,
    val level: String,
    val totalWords: Int,
    val completedWords: Int,
    val learningWords: Int
)