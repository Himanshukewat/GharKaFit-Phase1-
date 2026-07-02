package com.example.gharkafit.viewmodel

import com.example.gharkafit.data.IndianFoodData
import com.example.gharkafit.data.food.FoodEntity
import com.example.gharkafit.data.food.FoodRepository

class FoodSeeder(
    private val repository: FoodRepository
) {
    suspend fun seed() {
        val foods = IndianFoodData.foodList.map {
            FoodEntity(
                foodName = it.name,
                servingSize = 1.0,
                servingUnit = "Serving",
                calories = it.calories,
                protein = it.protein,
                carbs = 0.0,
                fat = 0.0
            )
        }
        repository.seedFoods(foods)
    }
}