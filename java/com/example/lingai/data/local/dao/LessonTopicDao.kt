package com.example.lingai.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lingai.data.local.entity.LessonTopicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonTopicDao {

    @Query("SELECT * FROM lesson_topic")
    fun getAllTopics(): Flow<List<LessonTopicEntity>>

    @Query("SELECT * FROM lesson_topic WHERE id = :id")
    suspend fun getTopicById(id: Int): LessonTopicEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopic(lessonTopic: LessonTopicEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopics(lessonTopics: List<LessonTopicEntity>)

    @Delete
    suspend fun deleteTopic(lessonTopic: LessonTopicEntity)

    @Query("DELETE FROM lesson_topic")
    suspend fun deleteAllTopics()
}