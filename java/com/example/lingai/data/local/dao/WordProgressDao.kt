package com.example.lingai.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lingai.data.local.entity.WordProgressEntity

@Dao
interface WordProgressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(p: WordProgressEntity)

    @Query("SELECT * FROM word_progress WHERE userId = :userId")
    suspend fun getAll(userId: String, ): List<WordProgressEntity>

    @Query("SELECT * FROM word_progress WHERE userId = :userId AND wordId = :wordId")
    suspend fun getAllWordsProgressByTopicId(userId: String, wordId: Int): List<WordProgressEntity>

    @Query("DELETE FROM word_progress WHERE userId = :userId")
    suspend fun deleteAll(userId: String)
}