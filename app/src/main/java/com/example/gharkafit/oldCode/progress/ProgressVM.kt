package com.example.gharkafit.oldCode.progress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gharkafit.data.meal.MealDao
import com.example.gharkafit.data.user.UserDao
import com.example.gharkafit.core.Calculator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProgressVM(
    private val mealDao: MealDao,
    private val userDao: UserDao
) : ViewModel() {

    private val _bmi = MutableStateFlow(0.0)
    val bmi: StateFlow<Double> = _bmi

    private val _weeklyCalories = MutableStateFlow(0)
    val weeklyCalories: StateFlow<Int> = _weeklyCalories

    private val _weeklyProtein = MutableStateFlow(0.0)
    val weeklyProtein: StateFlow<Double> = _weeklyProtein

    private val _calorieTarget = MutableStateFlow(2000)
    val calorieTarget: StateFlow<Int> = _calorieTarget

    init {
        viewModelScope.launch {
            val user = userDao.getUser()
            user.let {

                _bmi.value =
                    Calculator.calculateBMI(it.weightKg, it.heightCm)

                _calorieTarget.value =
                    Calculator.calculateDailyCalories(it)
            }

            val meals = mealDao.getTodayMeals()
            if (meals.isNotEmpty()) {

                _weeklyCalories.value =
                    meals.sumOf { it.calories }.toInt() / meals.size

                _weeklyProtein.value =
                    meals.sumOf { it.protein } / meals.size
            }
        }
    }
}