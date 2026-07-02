package com.example.gharkafit.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gharkafit.core.MealParser
import com.example.gharkafit.data.food.FoodEntity
import com.example.gharkafit.data.food.FoodRepository
import com.example.gharkafit.data.meal.MealLogEntity
import com.example.gharkafit.data.meal.MealRepository
import kotlinx.coroutines.launch

class MealViewModel(
    private val mealRepository: MealRepository,
    private val foodRepository: FoodRepository
) : ViewModel() {


    fun saveMeal(meal: MealLogEntity) {
        viewModelScope.launch {
            mealRepository.insertMeal(meal)
        }
    }

    fun getMeals(
        onResult: (List<MealLogEntity>) -> Unit
    ) {
        viewModelScope.launch {
            onResult(
                mealRepository.getTodayMeals()
            )
        }
    }

    fun checkMeals() {
        viewModelScope.launch {
            val meals = mealRepository.getTodayMeals()
            Log.d("ROOM_MEALS", meals.toString())
        }
    }

    fun checkFood(foodName: String) {
        viewModelScope.launch {
            val food = foodRepository.getFoodByName(foodName)
            Log.d("FOOD_CHECK", food.toString())
        }
    }

    fun analyzeMeal(
        input: String,
        onResult: (FoodEntity?) -> Unit
    ) {
        viewModelScope.launch {
            val words = MealParser.parseMeal(input)
//            Log.d("PARSER", words.toString())
            var food: FoodEntity? = null
            for (word in words) {
                food = foodRepository.getFoodByName(word)
//                Log.d("MATCH", "$word -> $food")
                if (food != null) {
                    break
                }
            }

            onResult(food)
        }
    }
}