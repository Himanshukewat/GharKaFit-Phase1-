package com.example.gharkafit.ui.screen.homeDash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gharkafit.data.meal.MealDao
import com.example.gharkafit.data.user.UserDao
import com.example.gharkafit.model.DailyFeedback
import com.example.gharkafit.model.FeedbackStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeDashVM(
    private val mealDao: MealDao,
    private val userDao: UserDao
) : ViewModel() {

    private val _calories = MutableStateFlow(0)
    val calories: StateFlow<Int> = _calories

    private val _protein = MutableStateFlow(0.0)
    val protein: StateFlow<Double> = _protein

    private val _calorieTarget = MutableStateFlow(2000)
    val calorieTarget: StateFlow<Int> = _calorieTarget

    private val _proteinTarget = MutableStateFlow(80)
    val proteinTarget: StateFlow<Int> = _proteinTarget

    private val _feedback =
        MutableStateFlow(
            DailyFeedback(
                "Keep going!",
                "You are doing well today",
                FeedbackStatus.GOOD_PROGRESS
            )
        )

    val feedback: StateFlow<DailyFeedback> = _feedback

    init {
        viewModelScope.launch {
            val meals = mealDao.getTodayMeals()
            _calories.value = meals.sumOf { it.calories }
            _protein.value = meals.sumOf { it.protein }

        }
    }
}