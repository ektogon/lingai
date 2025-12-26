package com.example.lingai.data.local.dao

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lingai.data.local.entity.WordEntity
import kotlinx.coroutines.flow.Flow

data class WordWithProgress(
    @Embedded val word: WordEntity,
    val progressStatus: String?,
    val lastReviewed: Long?,
    val reviewCount: Int?
)
@Dao
interface WordDao {

    @Query("SELECT * FROM words")
    fun getAllWords(): Flow<List<WordEntity>>

//    @Query("""
//        SELECT w.*,
//               p.status AS progressStatus,
//               p.lastReviewed AS lastReviewed,
//               p.reviewCount AS reviewCount
//        FROM words w
//        LEFT JOIN word_progress p
//          ON p.wordId = w.id AND p.userId = :userId
//        WHERE w.topicId = :topicId
//    """)
//    suspend fun getWordsForTopicWithProgress(topicId: Int, userId: String): List<WordWithProgress>

    @Query("SELECT * FROM words WHERE original = :original")
    fun getWordByOriginal(original: String): WordEntity?

    @Query("SELECT * FROM words WHERE topicId = :topicId ")
    suspend fun getWordsForTopic(topicId: Int): List<WordEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: WordEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWords(words: List<WordEntity>)
}