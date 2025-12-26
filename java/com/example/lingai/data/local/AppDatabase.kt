package com.example.lingai.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lingai.data.local.dao.LessonTopicDao
import com.example.lingai.data.local.dao.TopicProgressDao
import com.example.lingai.data.local.dao.WordDao
import com.example.lingai.data.local.dao.WordProgressDao
import com.example.lingai.data.local.entity.LessonTopicEntity
import com.example.lingai.data.local.entity.TopicProgressEntity
import com.example.lingai.data.local.entity.WordEntity
import com.example.lingai.data.local.entity.WordProgressEntity

@Database(
    entities = [
        LessonTopicEntity::class,
        WordEntity::class,
        WordProgressEntity::class,
        TopicProgressEntity::class
               ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lessonTopicDao(): LessonTopicDao
    abstract fun wordDao(): WordDao
    abstract fun wordProgressDao(): WordProgressDao
    abstract fun topicProgressDao(): TopicProgressDao

//    companion object {
//        // 🚀 Миграция с v1 до v2 – создаём таблицы прогресса
//        val MIGRATION_1_2 = object : Migration(1, 2) {
//            override fun migrate(db: SupportSQLiteDatabase) {
//                // Таблица прогресса по словам
//                db.execSQL(
//                    """
//                    CREATE TABLE IF NOT EXISTS word_progress (
//                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
//                        wordId INTEGER NOT NULL,
//                        userId TEXT,
//                        status TEXT NOT NULL,
//                        lastReviewed TEXT,
//                        reviewCount INTEGER NOT NULL DEFAULT 0
//                    )
//                    """.trimIndent()
//                )
//
//                // Таблица прогресса по темам
//                db.execSQL(
//                    """
//                    CREATE TABLE IF NOT EXISTS topic_progress (
//                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
//                        topicId INTEGER NOT NULL,
//                        userId TEXT,
//                        status TEXT NOT NULL,
//                        completedWords INTEGER NOT NULL DEFAULT 0,
//                        totalWords INTEGER NOT NULL DEFAULT 0,
//                        lastReviewed TEXT
//                    )
//                    """.trimIndent()
//                )
//            }
//        }
//    }
}