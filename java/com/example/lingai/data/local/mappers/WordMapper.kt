package com.example.lingai.data.local.mappers

import com.example.lingai.data.local.entity.WordEntity
import com.example.lingai.domain.model.Word
import com.example.lingai.domain.model.WordStatus
import javax.inject.Inject

class WordMapper @Inject constructor() {

    fun fromEntity(entity: WordEntity): Word {
        return Word(
            original = entity.original,
            translation = entity.translation,
            status = WordStatus.valueOf(entity.status.uppercase())
        )
    }

    fun fromEntityList(list: List<WordEntity>): List<Word> {
        return list.map { fromEntity(it) }
    }
}