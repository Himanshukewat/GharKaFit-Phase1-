package com.example.gharkafit.ui.screen.userDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gharkafit.data.user.UserDao
import com.example.gharkafit.data.user.UserEntity
import kotlinx.coroutines.launch

class UserDetailVM(
    private val userDao: UserDao
) : ViewModel() {

    fun saveUser(
        age: Int,
        height: Double,
        weight: Double,
        gender: String
    ) {

        viewModelScope.launch {

            val user = UserEntity(
                age = age,
                heightCm = height,
                weightKg = weight,
                gender = gender,
                goal = "maintain",
                activityLevel = "sedentary",
                targetCalories = 0,
                targetProtein = 0.0,
                name = "",
                dietHabit = ""
            )

            userDao.insertUser(user)
        }
    }
}