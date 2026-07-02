package com.example.gharkafit.core

import com.example.gharkafit.data.food.FoodEntity
import com.example.gharkafit.model.MealAnalysisResult

class MealAnalyzer {

    fun analyze(
        food: FoodEntity?,
        quantity: Double = 1.0,
        unit: String = "Serving"
    ): MealAnalysisResult {

        if (food == null) {
            return MealAnalysisResult(
                foodName = "Unknown",
                quantity = quantity,
                unit = unit,
                calories = 0,
                protein = 0.0,
                carbs = 0.0,
                fat = 0.0,
                source = "UNKNOWN"
            )
        }

        return MealAnalysisResult(
            foodName = food.foodName,
            quantity = quantity,
            unit = unit,
            calories = (food.calories * quantity).toInt(),
            protein = food.protein * quantity,
            carbs = food.carbs * quantity,
            fat = food.fat * quantity,
            source = "DATABASE"
        )
    }
}