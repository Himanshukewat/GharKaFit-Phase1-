package com.example.gharkafit.ai

import kotlinx.serialization.Serializable


@Serializable
data class MealAnalysisResult(
    val foods: List<FoodItemAI>,
    val summary: NutritionSummary
)
