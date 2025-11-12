package com.example.lingai.data

data class WordItem(
    val text: String,
    val translation: String,
    val status: WordStatus
)

enum class WordStatus { LEARNED, IN_PROGRESS, NEW }