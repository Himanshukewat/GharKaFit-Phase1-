package com.example.gharkafit.core

import com.example.gharkafit.model.MealAnalysisResult

// when use ai then only change that file , and remaining are same
class MealInsightGenerator {
    fun generate(result: MealAnalysisResult): String {
        return when {
            result.protein >= 20 ->
                "Excellent protein source."
            result.protein >= 10 ->
                "Good protein content."
            result.carbs >= 50 ->
                "Rich in carbohydrates."
            result.fat >= 15 ->
                "Higher fat meal."
            result.calories >= 500 ->
                "Energy-dense meal."
            result.calories < 200 ->
                "Light meal."
            else ->
                "Balanced meal."

        }
    }
}