package com.example.lingai.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.lingai.data.local.AppDatabase
import com.example.lingai.data.local.dao.LessonTopicDao
import com.example.lingai.data.local.dao.WordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
//    val MIGRATION_3_4 = object : Migration(3, 4) {
//        override fun migrate(db: SupportSQLiteDatabase) {
//            db.execSQL("ALTER TABLE lesson_topic ADD COLUMN ______ TEXT NOT NULL DEFAULT ''")
//        }
//    }

    // Предоставляем базу данных
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "lingai_db"
        )
            .fallbackToDestructiveMigration()
            .createFromAsset("lingai.db")
//            .addMigrations(MIGRATION_3_4)
            .build()

    }

    // Предоставляем LessonTopicDao
    @Provides
    @Singleton
    fun provideLessonTopicDao(database: AppDatabase): LessonTopicDao {
        return database.lessonTopicDao()
    }

    // Предоставляем WordDao
    @Provides
    @Singleton
    fun provideWordDao(database: AppDatabase): WordDao {
        return database.wordDao()
    }
}
