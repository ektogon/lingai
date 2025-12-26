package com.example.lingai.data.local.mappers

import com.example.lingai.data.local.entity.LessonTopicEntity
import com.example.lingai.data.local.entity.TopicProgressEntity
import com.example.lingai.data.local.entity.WordEntity
import com.example.lingai.data.local.entity.WordProgressEntity
import com.example.lingai.domain.models.LessonTopicModel
import javax.inject.Inject

class LessonTopicMapper @Inject constructor(private val wordMapper: WordMapper) {
    fun mapTopic(
        topic: LessonTopicEntity,
        topicProgress: TopicProgressEntity?,
        words: List<WordEntity>,
        wordProgress: List<WordProgressEntity>? = null
    ): LessonTopicModel {
        return LessonTopicModel(
            id = topic.id,
            title = topic.title,
            translation = topic.translation,
            level = topic.level,
            emoji = topic.emoji,
            status = topicProgress?.status,
            learningWords = topicProgress?.learningWords,
            completedWords = topicProgress?.completedWords,
            totalWords = words.size,
            words = wordMapper.fromEntityList(words, wordProgress)
        )
    }
}