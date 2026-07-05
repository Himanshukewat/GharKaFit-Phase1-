package com.example.gharkafit.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gharkafit.model.ActivityLevel
import com.example.gharkafit.model.DietHabit
import com.example.gharkafit.model.Gender
import com.example.gharkafit.model.Goal
import com.example.gharkafit.model.OnboardingUiState
import com.example.gharkafit.core.Calculator
import com.example.gharkafit.core.TargetWeightCalculator
import com.example.gharkafit.data.user.UserEntity
import com.example.gharkafit.data.user.UserRepository
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val repository: UserRepository
) : ViewModel() {


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

    fun validateInput(): String? {

        if (uiState.name.isBlank()) {
            return "Please enter your name"
        }

        if (uiState.age.isBlank()) {
            return "Please enter your age"
        }

        if (uiState.height.isBlank()) {
            return "Please enter your height"
        }

        if (uiState.weight.isBlank()) {
            return "Please enter your weight"
        }

        if (uiState.age.toIntOrNull() == null || uiState.age.toInt() <= 0) {
            return "Enter a valid age"
        }

        if (uiState.height.toDoubleOrNull() == null || uiState.height.toDouble() <= 0) {
            return "Enter a valid height"
        }

        if (uiState.weight.toDoubleOrNull() == null || uiState.weight.toDouble() <= 0) {
            return "Enter a valid weight"
        }

        return null
    }

    fun createUserEntity(): UserEntity {

        val age = uiState.age.trim().toInt()
        val height = uiState.height.trim().toDouble()
        val weight = uiState.weight.trim().toDouble()

        val calories = Calculator.calculateDailyCalories(
            weightKg = weight,
            heightCm = height,
            age = age,
            gender = uiState.gender.name,
            activityLevel = uiState.activityLevel.name,
            goal = uiState.goal.name
        )

        val protein = Calculator.calculateDailyProtein(
            weightKg = weight,
            goal = uiState.goal.name
        )

        //🤖 Auto Calculate (BMI based)
        //✍️ Set My Own Target Weight

        Log.d("HEIGHT", height.toString())
        Log.d("WEIGHT", weight.toString())
        Log.d("GOAL", uiState.goal.name)

        val targetWeight = TargetWeightCalculator.calculate(
            heightCm = height,
            currentWeight = weight,
            goal = uiState.goal.name
        )

        val waterTarget = when (uiState.activityLevel.name) {
            "SEDENTARY" -> 2.5
            "LIGHT" -> 3.0
            "ACTIVE" -> 4.0
            else -> 3.0
        }

        return UserEntity(
            name = uiState.name,
            age = age,
            gender = uiState.gender.name,
            heightCm = height,
            weightKg = weight,
            startWeight = weight,
            targetWeight = targetWeight,
            goal = uiState.goal.name,
            dietHabit = uiState.dietHabit.name,
            activityLevel = uiState.activityLevel.name,
            targetCalories = calories,
            targetProtein = protein,
            waterTarget = waterTarget
        )
    }

    fun saveUser(user: UserEntity) {
        viewModelScope.launch {
            repository.deleteAllUsers()
            repository.insertUser(user)
        }
    }

//    fun checkUser() {
//        viewModelScope.launch {
//            val user = repository.getUser()
//            Log.d("ROOM_USER", user.toString())
//        }
//    }
      /** for onboarding screen
    fun hasUser(
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            onResult(
                repository.hasUser()
            )
        }
    }
    */
}