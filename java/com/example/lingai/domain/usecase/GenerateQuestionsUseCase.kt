package com.example.lingai.domain.usecase

import com.example.lingai.domain.model.Question
import com.example.lingai.domain.model.Word
import javax.inject.Inject

class GenerateQuestionsUseCase @Inject constructor() {

    fun invoke(words: List<Word>): List<Question> {
        return words.map { correct ->
            val distractors = words
                .filter { it.original != correct.original }
                .shuffled()
                .take(3)

            val shuffled = (distractors + correct).shuffled()

            Question(
                correctAnswer = correct,
                variants = shuffled,
                correctIndex = shuffled.indexOf(correct)
            )
        }
    }
}
