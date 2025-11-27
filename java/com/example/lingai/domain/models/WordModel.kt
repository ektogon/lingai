package com.example.lingai.domain.models

data class Word(
    val original: String,
    val translation: String,
    val transcription: String,
    val status: WordStatus
)

enum class WordStatus { LEARNED, IN_PROGRESS, NEW }