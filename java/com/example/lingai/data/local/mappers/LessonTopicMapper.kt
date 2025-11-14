package com.example.lingai.data.local.mappers

import com.example.lingai.data.local.entity.LessonTopicEntity
import com.example.lingai.data.local.entity.WordEntity
import com.example.lingai.domain.model.LessonTopic
import javax.inject.Inject

class LessonTopicMapper @Inject constructor(private val wordMapper: WordMapper) {
    fun mapTopic(
        topic: LessonTopicEntity,
        words: List<WordEntity>
    ): LessonTopic {
        return LessonTopic(
            id = topic.id,
            title = topic.title,
            totalWords = topic.totalWords,
            completedWords = topic.completedWords,
            learningWords = topic.learningWords,
            level = topic.level,
            emoji = topic.emoji,
            words = wordMapper.fromEntityList(words)
        )
    }
}