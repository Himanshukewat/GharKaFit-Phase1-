package com.example.gharkafit.oldCode.foodPicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gharkafit.data.meal.MealDao
import com.example.gharkafit.data.meal.MealLogEntity
import com.example.gharkafit.model.FoodItem
import kotlinx.coroutines.launch

class FoodPickerVM(
    private val mealDao: MealDao
) : ViewModel() {

    fun addFood(food: FoodItem, quantity: Int) {

        viewModelScope.launch {

            val meal = MealLogEntity(
                foodName = food.name,
                quantity = quantity.toDouble(),
                calories = (food.calories * quantity),
                protein = food.protein * quantity,
                carbs = 0.0,
                fat = 0.0,
                mealType = "meal",
                date = System.currentTimeMillis().toString()
            )

            mealDao.insertMeal(meal)
        }
    }
}