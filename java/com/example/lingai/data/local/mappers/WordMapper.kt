package com.example.lingai.data.local.mappers

import com.example.lingai.data.local.entity.WordEntity
import com.example.lingai.data.local.entity.WordProgressEntity
import com.example.lingai.domain.models.WordModel
import javax.inject.Inject

class WordMapper @Inject constructor() {

    private companion object {
        const val DEFAULT_STATUS = "NEW"
    }

    fun fromEntity(
        word: WordEntity,
        progress: WordProgressEntity? = null
    ): WordModel {
        return WordModel(
            id = word.id,
            original = word.original,
            translation = word.translation,
            transcription = word.transcription,
            status = progress?.status ?: DEFAULT_STATUS,
            lastReviewed = progress?.lastReviewed ?: "",
            reviewCount = progress?.reviewCount ?: 0
        )
    }

    fun fromEntityList(
        words: List<WordEntity>,
        progressList: List<WordProgressEntity>? = null
    ): List<WordModel> {
        if (progressList == null) return words.map { fromEntity(it) }
        else {
            val progressByWordId = progressList.associateBy { it.wordId }
            return words.map { word ->
                fromEntity(word, progressByWordId[word.id])
            }
        }
    }
}