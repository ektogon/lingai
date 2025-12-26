package com.example.lingai.domain.models

enum class WordStatus { NEW, IN_PROGRESS, LEARNED }
data class WordModel(
    val id: Int,
    val original: String,
    val translation: String,
    val transcription: String,
    val status: String? = null,
    val lastReviewed: String? = null,
    val reviewCount: Int? = null
)