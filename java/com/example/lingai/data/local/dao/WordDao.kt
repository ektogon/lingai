package com.example.lingai.data.local.dao

import androidx.room.*
import com.example.lingai.data.local.entity.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Query("SELECT * FROM words")
    fun getAllWords(): Flow<List<WordEntity>>

    @Query("SELECT * FROM words WHERE status = :status")
    fun getWordsByStatus(status: String): Flow<List<WordEntity>>

    @Query("SELECT * FROM words WHERE original = :original")
    fun getWordByOriginal(original: String): WordEntity?

    @Query("SELECT * FROM words WHERE topicId = :topicId ")
    suspend fun getWordsForTopic(topicId: Int): List<WordEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: WordEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWords(words: List<WordEntity>)
}