package com.example.lingai.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lingai.data.local.dao.LessonTopicDao
import com.example.lingai.data.local.dao.WordDao
import com.example.lingai.data.local.entity.LessonTopicEntity
import com.example.lingai.data.local.entity.WordEntity

@Database(
    entities = [LessonTopicEntity::class, WordEntity::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lessonTopicDao(): LessonTopicDao
    abstract fun wordDao(): WordDao
}