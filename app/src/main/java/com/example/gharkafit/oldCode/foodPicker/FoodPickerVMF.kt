package com.example.gharkafit.oldCode.foodPicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gharkafit.data.meal.MealDao

class FoodPickerVMF(
    private val mealDao: MealDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(FoodPickerVM::class.java)) {
            return FoodPickerVM(mealDao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}