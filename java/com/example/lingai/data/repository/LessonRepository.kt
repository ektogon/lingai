package com.example.lingai.data.repository

import com.example.lingai.data.local.dao.LessonTopicDao
import com.example.lingai.data.local.dao.WordDao
import com.example.lingai.data.local.entity.LessonTopicEntity
import com.example.lingai.data.local.entity.WordEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LessonRepository @Inject constructor(
    private val topicDao: LessonTopicDao,
    private val wordDao: WordDao
) {
    fun getAllTopics(): Flow<List<LessonTopicEntity>> = topicDao.getAllTopics()
    fun getWordsByTopic(topicId: String): Flow<List<WordEntity>> = wordDao.getWordsByTopic(topicId)

    suspend fun insertTopics(topics: List<LessonTopicEntity>) = topicDao.insertTopics(topics)
    suspend fun insertWords(words: List<WordEntity>) = wordDao.insertWords(words)
}