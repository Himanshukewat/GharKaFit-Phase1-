package com.example.gharkafit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gharkafit.data.food.FoodRepository
import com.example.gharkafit.data.meal.MealRepository
import com.example.gharkafit.data.remote.FirestoreRepository

class MealViewModelFactory(
    private val mealRepository: MealRepository,
    private val foodRepository: FoodRepository,
    private val firestoreRepository: FirestoreRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MealViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MealViewModel(
                mealRepository,
                foodRepository,
                firestoreRepository
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }
}