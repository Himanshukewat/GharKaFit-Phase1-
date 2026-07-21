package com.example.gharkafit.ai

import kotlinx.serialization.Serializable

@Serializable
data class MealAnalysisResult(
    val mealName: String,
    val calories: Int,
    val protein: Double,
    val quantity: String,
    val confidence: Double
)