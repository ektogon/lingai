package com.example.lingai.di.modules

import android.content.Context
import androidx.room.Room
import com.example.lingai.data.local.AppDatabase
import com.example.lingai.data.local.dao.LessonTopicDao
import com.example.lingai.data.local.dao.TopicProgressDao
import com.example.lingai.data.local.dao.WordDao
import com.example.lingai.data.local.dao.WordProgressDao
import com.example.lingai.tts.TtsSpeaker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Предоставляем базу данных
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "lingai_db"
        )
            .createFromAsset("lingai_prepopulate.db")
            .build()

    }

    // Предоставляем LessonTopicDao
    @Provides
    @Singleton
    fun provideLessonTopicDao(database: AppDatabase): LessonTopicDao {
        return database.lessonTopicDao()
    }

    @Provides
    @Singleton
    fun provideTopicProgressDao(database: AppDatabase): TopicProgressDao {
        return database.topicProgressDao()
    }


    // Предоставляем WordDao
    @Provides
    @Singleton
    fun provideWordDao(database: AppDatabase): WordDao {
        return database.wordDao()
    }

    @Provides
    @Singleton
    fun provideWordProgressDao(database: AppDatabase): WordProgressDao {
        return database.wordProgressDao()
    }

    @Provides
    @Singleton
    fun provideTtsSpeaker(
        @ApplicationContext context: Context
    ): TtsSpeaker = TtsSpeaker(context)
}