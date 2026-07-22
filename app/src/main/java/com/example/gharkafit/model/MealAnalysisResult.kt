package com.example.gharkafit.model

import kotlinx.serialization.Serializable

@Serializable
data class MealAnalysisResult(
    val foodName: String,
    val quantity: Double,
    val unit: String,
    val calories: Int,
    val protein: Double,
    val carbs: Double,
    val fat: Double
)