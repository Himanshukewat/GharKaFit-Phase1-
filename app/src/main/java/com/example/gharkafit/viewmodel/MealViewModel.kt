package com.example.gharkafit.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gharkafit.data.meal.MealLogEntity
import com.example.gharkafit.data.meal.MealRepository
import kotlinx.coroutines.launch

class MealViewModel(
    private val repository: MealRepository
) : ViewModel() {


    fun saveMeal(meal: MealLogEntity) {
        viewModelScope.launch {
            repository.insertMeal(meal)
        }
    }

    fun getMeals(
        onResult: (List<MealLogEntity>) -> Unit
    ) {
        viewModelScope.launch {
            onResult(
                repository.getTodayMeals()
            )
        }
    }

    fun checkMeals() {
        viewModelScope.launch {
            val meals = repository.getTodayMeals()
            Log.d("ROOM_MEALS", meals.toString())
        }
    }
}