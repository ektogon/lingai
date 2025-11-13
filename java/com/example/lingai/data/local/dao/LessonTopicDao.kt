package com.example.lingai.data.local.dao

import androidx.room.*
import com.example.lingai.data.local.entity.LessonTopicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonTopicDao {

    @Query("SELECT * FROM lesson_topic")
    fun getAllTopics(): Flow<List<LessonTopicEntity>>

    @Query("SELECT * FROM lesson_topic WHERE id = :id")
    fun getTopicById(id: String): LessonTopicEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopic(lessonTopic: LessonTopicEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopics(lessonTopics: List<LessonTopicEntity>)

    @Delete
    suspend fun deleteTopic(lessonTopic: LessonTopicEntity)

    @Query("DELETE FROM lesson_topic")
    suspend fun deleteAllTopics()
}