package com.example.gharkafit.ai

import kotlinx.serialization.Serializable

@Serializable
data class NutritionSummary(
    val calories: Int,
    val protein: Double,
    val carbs: Double,
    val fat: Double
)
