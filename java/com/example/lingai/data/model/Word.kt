package com.example.lingai.data.model

data class Word(
    val original: String,
    val translation: String,
    val status: WordStatus
)

enum class WordStatus { LEARNED, IN_PROGRESS, NEW }