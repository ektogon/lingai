package com.example.lingai.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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