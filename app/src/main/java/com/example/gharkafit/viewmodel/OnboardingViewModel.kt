package com.example.gharkafit.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.gharkafit.model.ActivityLevel
import com.example.gharkafit.model.DietHabit
import com.example.gharkafit.model.Gender
import com.example.gharkafit.model.Goal
import com.example.gharkafit.model.OnboardingUiState

class OnboardingViewModel : ViewModel() {

    var uiState by mutableStateOf(OnboardingUiState())
        private set

    fun updateName(name: String) {
        uiState = uiState.copy(name = name)
    }

    fun updateAge(age: String) {
        uiState = uiState.copy(age = age)
    }

    fun updateHeight(height: String) {
        uiState = uiState.copy(height = height)
    }

    fun updateWeight(weight: String) {
        uiState = uiState.copy(weight = weight)
    }

    fun updateGender(gender: Gender) {
        uiState = uiState.copy(gender = gender)
    }

    fun updateGoal(goal: Goal) {
        uiState = uiState.copy(goal = goal)
    }

    fun updateActivity(activity: ActivityLevel) {
        uiState = uiState.copy(activityLevel = activity)
    }

    fun updateDiet(diet: DietHabit) {
        uiState = uiState.copy(dietHabit = diet)
    }
}