package com.example.lingai.domain.repository

interface ProgressRepository {
    suspend fun attachGuestProgressToUser(userId: String)
    suspend fun clearGuestProgress()
}