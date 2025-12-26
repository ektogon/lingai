package com.example.lingai.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "word_progress",
    primaryKeys = ["wordId", "userId"],
    foreignKeys = [
        ForeignKey(
            entity = WordEntity::class,
            parentColumns = ["id"],
            childColumns = ["wordId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("wordId"), Index("userId")]
)
data class WordProgressEntity(
    @ColumnInfo(name = "wordId")
    val wordId: Int,  // ID слова (внешний ключ на таблицу слов)

    @ColumnInfo(name = "userId")
    val userId: String,  // ID пользователя

    @ColumnInfo(name = "status")
    val status: String,  // Статус слова (например, "NEW", "IN_PROGRESS", "LEARNED")

    @ColumnInfo(name = "lastReviewed")
    val lastReviewed: String,  // Дата последнего изучения

    @ColumnInfo(name = "reviewCount")
    val reviewCount: Int  // Количество повторений слова
)

