package com.example.gharkafit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gharkafit.data.food.FoodRepository
import com.example.gharkafit.data.meal.MealRepository
import com.example.gharkafit.data.remote.FirestoreRepository
import com.example.gharkafit.data.remote.MealFirestoreRepository

class MealViewModelFactory(
    private val mealRepository: MealRepository,
    private val foodRepository: FoodRepository,
    private val firestoreRepository: FirestoreRepository,
    private val mealFirestoreRepository: MealFirestoreRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MealViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MealViewModel(
                mealRepository,
                foodRepository,
                firestoreRepository,
                mealFirestoreRepository
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }
}