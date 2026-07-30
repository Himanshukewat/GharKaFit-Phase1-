package com.example.gharkafit.ai

import kotlinx.serialization.Serializable

@Serializable
data class FoodItemAI(
    val foodName: String,
    val quantity: Double,
    val unit: String,
    val calories: Int,
    val protein: Double,
    val carbs: Double,
    val fat: Double
)
