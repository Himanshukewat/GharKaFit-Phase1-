package com.example.gharkafit.oldCode.dailySummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gharkafit.data.meal.MealDao
import com.example.gharkafit.data.user.UserDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DailySummaryVM(
    private val mealDao: MealDao,
    private val userDao: UserDao
) : ViewModel() {

    private val _calories = MutableStateFlow(0)
    val calories: StateFlow<Int> = _calories

    private val _protein = MutableStateFlow(0.0)
    val protein: StateFlow<Double> = _protein

    init {

        viewModelScope.launch {

            val meals = mealDao.getTodayMeals()

            _calories.value = meals.sumOf { it.calories }.toInt()

            _protein.value = meals.sumOf { it.protein }
        }
    }
}