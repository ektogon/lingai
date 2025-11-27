package com.example.lingai.domain.repository

import com.example.lingai.data.local.dao.LessonTopicDao
import com.example.lingai.data.local.dao.WordDao
import com.example.lingai.data.local.entity.LessonTopicEntity
import com.example.lingai.data.local.entity.WordEntity
import com.example.lingai.data.local.mappers.LessonTopicMapper
import com.example.lingai.domain.models.LessonTopicModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LessonRepository @Inject constructor(
    private val topicDao: LessonTopicDao,
    private val wordDao: WordDao,
    private val topicMapper: LessonTopicMapper
) {
    fun getAllTopics(): Flow<List<LessonTopicEntity>> = topicDao.getAllTopics()
    suspend fun getWordsForTopic(topicId: Int): List<WordEntity> = wordDao.getWordsForTopic(topicId)
    fun getTopicWithWords(): Flow<List<LessonTopicModel>> {
        return topicDao.getAllTopics().map { topicEntities ->
            topicEntities.map { topicEntity ->
                val words = wordDao.getWordsForTopic(topicEntity.id)
                topicMapper.mapTopic(topicEntity, words)
            }
        }
    }
    suspend fun getTopicWithWordsById(id: Int): LessonTopicModel {
        val topic = topicDao.getTopicById(id)?:throw Exception("Topic not found")
        val words = wordDao.getWordsForTopic(id)
        return topicMapper.mapTopic(topic, words)
    }


    suspend fun getTopicById(id: Int): LessonTopicModel? {
        val topic = topicDao.getTopicById(id) ?: return null
        val words = wordDao.getWordsForTopic(id)
        return topicMapper.mapTopic(topic, words)
    }

    suspend fun insertTopics(topics: List<LessonTopicEntity>) = topicDao.insertTopics(topics)
    suspend fun insertWords(words: List<WordEntity>) = wordDao.insertWords(words)
}