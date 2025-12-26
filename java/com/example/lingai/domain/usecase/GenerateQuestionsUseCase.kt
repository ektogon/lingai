package com.example.lingai.domain.usecase

import com.example.lingai.domain.models.QuestionModel
import com.example.lingai.domain.models.WordModel
import javax.inject.Inject

class GenerateQuestionsUseCase @Inject constructor() {

    fun invoke(words: List<WordModel>): List<QuestionModel> {
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
