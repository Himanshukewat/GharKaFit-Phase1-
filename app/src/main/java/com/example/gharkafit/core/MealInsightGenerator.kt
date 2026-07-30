package com.example.gharkafit.core

import com.example.gharkafit.ai.MealAnalysisResult

// when use ai then only change that file , and remaining are same
class MealInsightGenerator {
    fun generate(result: MealAnalysisResult): String {
        val summary = result.summary

        return when {
            summary.protein >= 20 ->
                "Excellent protein source."
            summary.protein >= 10 ->
                "Good protein content."
            summary.carbs >= 50 ->
                "Rich in carbohydrates."
            summary.fat >= 15 ->
                "Higher fat meal."
            summary.calories >= 500 ->
                "Energy-dense meal."
            summary.calories < 200 ->
                "Light meal."
            else ->
                "Balanced meal."
        }
    }
}