package com.example.gharkafit.ui.screen.resultBMI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gharkafit.data.user.UserDao
import com.example.gharkafit.data.user.UserEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ResultBMIVM(
    private val userDao: UserDao
) : ViewModel() {

    private val _user = MutableStateFlow<UserEntity?>(null)
    val user: StateFlow<UserEntity?> = _user

    init {
        viewModelScope.launch {
            _user.value = userDao.getUser()
        }
    }

    fun bmiMessage(bmi: Double): String {

        return when {
            bmi < 18.5 -> "Underweight"
            bmi < 25 -> "Normal weight"
            bmi < 30 -> "Overweight"
            else -> "Obese"
        }
    }

    fun proteinMessage(dietHabit: String): String {

        return when (dietHabit) {

            "PROCESSED" ->
                "We’ll gradually improve your protein intake."

            "MIXED" ->
                "Nice balance! Let’s optimize protein."

            else ->
                "Strong base! Just maintain consistency."
        }
    }
}