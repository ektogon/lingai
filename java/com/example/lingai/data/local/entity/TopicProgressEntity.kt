package com.example.lingai.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "topic_progress",
    primaryKeys = ["topicId", "userId"],
    foreignKeys = [
        ForeignKey(
            entity = LessonTopicEntity::class,
            parentColumns = ["id"],
            childColumns = ["topicId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("topicId"), Index("userId")]
)
data class TopicProgressEntity(
    @ColumnInfo(name = "topicId")
    val topicId: Int,  // ID темы (внешний ключ на таблицу тем)

    @ColumnInfo(name = "userId")
    val userId: String,  // ID пользователя

    @ColumnInfo(name = "status")
    val status: String,  // Статус темы (например, "NEW", "IN_PROGRESS", "COMPLETED")

    @ColumnInfo(name = "learningWords")
    val learningWords: Int,  // Количество изучаемых слов в этой теме

    @ColumnInfo(name = "completedWords")
    val completedWords: Int,  // Количество выученных слов в этой теме

    @ColumnInfo(name = "totalWords")
    val totalWords: Int,  // Общее количество слов в этой теме

    @ColumnInfo(name = "lastReviewed")
    val lastReviewed: String  // Дата последнего изучения темы
)
