package com.example.lingai.domain.usecase

import com.example.lingai.domain.models.QuestionModel
import com.example.lingai.domain.models.Word
import javax.inject.Inject

class GenerateQuestionsUseCase @Inject constructor() {

    fun invoke(words: List<Word>): List<QuestionModel> {
        return words.map { correct ->
            val distractors = words
                .filter { it.original != correct.original }
                .shuffled()
                .take(3)

            val shuffled = (distractors + correct).shuffled()

            QuestionModel(
                correctAnswer = correct,
                variants = shuffled,
                correctIndex = shuffled.indexOf(correct)
            )
        }
    }
}
