package com.example.lingai.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lingai.data.local.entity.TopicProgressEntity

@Dao
interface TopicProgressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(p: TopicProgressEntity)

    @Query("SELECT * FROM topic_progress WHERE userId = :userId")
    suspend fun getAllTopicProgress(userId: String): List<TopicProgressEntity>

    @Query("SELECT * FROM topic_progress WHERE userId = :userId AND topicId = :topicId")
    suspend fun getTopicProgress(userId: String, topicId: Int): TopicProgressEntity

    @Query("DELETE FROM topic_progress WHERE userId = :userId")
    suspend fun deleteAll(userId: String)
}