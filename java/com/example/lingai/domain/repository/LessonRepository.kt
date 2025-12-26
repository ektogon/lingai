package com.example.lingai.domain.repository

import com.example.lingai.data.local.dao.LessonTopicDao
import com.example.lingai.data.local.dao.TopicProgressDao
import com.example.lingai.data.local.dao.WordDao
import com.example.lingai.data.local.dao.WordProgressDao
import com.example.lingai.data.local.entity.LessonTopicEntity
import com.example.lingai.data.local.entity.WordEntity
import com.example.lingai.data.local.mappers.LessonTopicMapper
import com.example.lingai.domain.models.LessonTopicModel
import com.example.lingai.domain.session.SessionIds.GUEST
import com.example.lingai.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LessonRepository @Inject constructor(
    private val topicDao: LessonTopicDao,
    private val wordDao: WordDao,
    private val wordProgressDao: WordProgressDao,
    private val topicMapper: LessonTopicMapper,
    private val topicProgressDao: TopicProgressDao,
    private val authRepository: AuthRepository,
) {
    suspend fun getUserId(): String {
        return try {
            var userIdResult: String? = null
            authRepository.getCurrentUser().collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        userIdResult = result.data?.userId
                    }
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Error -> {}
                }
            }
            userIdResult ?: GUEST
        } catch (e: Exception) {
            GUEST
        }
    }

    fun getTopicWithWords(): Flow<List<LessonTopicModel>> {
        return topicDao.getAllTopics().map { topicEntities ->
            topicEntities.map { topicEntity ->
                val words = wordDao.getWordsForTopic(topicEntity.id)
                val wordProgress = wordProgressDao.getAllWordsProgressByTopicId(getUserId(), topicEntity.id)
                val topicProgress = topicProgressDao.getTopicProgress(getUserId(),topicEntity.id)
                topicMapper.mapTopic(topicEntity, topicProgress = topicProgress, words = words, wordProgress = wordProgress)
            }
        }
    }

    suspend fun getTopicWithWordsById(id: Int): LessonTopicModel {
        val topic = topicDao.getTopicById(id) ?: throw Exception("Topic not found")
        val topicProgress = topicProgressDao.getTopicProgress(getUserId(),topic.id)
        val words = wordDao.getWordsForTopic(id)
        val wordProgress = wordProgressDao.getAllWordsProgressByTopicId(getUserId(), topic.id)
        return topicMapper.mapTopic(topic, topicProgress, words, wordProgress)
    }


//        //
//        // Получение прогресса по слову
//        suspend fun getWordProgress(wordId: Int, userId: String?): WordProgressEntity? {
//            return wordProgressDao.getWordProgress(wordId, userId)
//        }
//
//    // Получение прогресса по теме
//    suspend fun getTopicProgress(topicId: Int, userId: String?): TopicProgressEntity? {
//        return topicProgressDao.getTopicProgress(topicId, userId)
//    }
//
//    // Обновление прогресса по слову
//    suspend fun updateWordProgress(wordId: Int, userId: String?, status: WordStatus, lastReviewed: String) {
//        wordProgressDao.updateWordProgress(wordId, userId, status.name, lastReviewed)
//    }
//
//
////enum сюда добавить
//    // Обновление прогресса по теме
//    suspend fun updateTopicProgress(topicId: Int, userId: String?, status: String, completedWords: Int, lastReviewed: String) {
//        topicProgressDao.updateTopicProgress(topicId, userId, status, completedWords, lastReviewed)
//    }
//
//    // Вставка прогресса по слову
//    suspend fun insertWordProgress(wordProgress: WordProgressEntity) {
//        wordProgressDao.insertWordProgress(wordProgress)
//    }
//
//    // Вставка прогресса по теме
//    suspend fun insertTopicProgress(topicProgress: TopicProgressEntity) {
//        topicProgressDao.insertTopicProgress(topicProgress)
//    }
}