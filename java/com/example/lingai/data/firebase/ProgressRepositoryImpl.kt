package com.example.lingai.data.firebase

import androidx.room.withTransaction
import com.example.lingai.data.local.AppDatabase
import com.example.lingai.data.local.dao.TopicProgressDao
import com.example.lingai.data.local.dao.WordProgressDao
import com.example.lingai.domain.repository.ProgressRepository
import com.example.lingai.domain.session.SessionIds
import javax.inject.Inject

class ProgressRepositoryImpl @Inject constructor(
    private val db: AppDatabase,
    private val wordProgressDao: WordProgressDao,
    private val topicProgressDao: TopicProgressDao
) : ProgressRepository {
    override suspend fun attachGuestProgressToUser(userId: String) {
        if (userId == SessionIds.GUEST) return

        db.withTransaction {
            val guestWord = wordProgressDao.getAll(SessionIds.GUEST)
            guestWord.forEach { p -> wordProgressDao.upsert(p.copy(userId = userId)) }

            val guestTopic = topicProgressDao.getAllTopicProgress(SessionIds.GUEST)
            guestTopic.forEach { p -> topicProgressDao.upsert(p.copy(userId = userId)) }

            // очищаем гостя после переноса
            wordProgressDao.deleteAll(SessionIds.GUEST)
            topicProgressDao.deleteAll(SessionIds.GUEST)
        }
    }

    override suspend fun clearGuestProgress() {
        db.withTransaction {
            wordProgressDao.deleteAll(SessionIds.GUEST)
            topicProgressDao.deleteAll(SessionIds.GUEST)
        }
    }
}
