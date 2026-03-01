package com.example.gharkafit.ui.screen.homeDash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gharkafit.data.meal.MealDao
import com.example.gharkafit.data.user.UserDao

class HomeDashVMF(
    private val mealDao: MealDao,
    private val userDao: UserDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HomeDashVM::class.java)) {
            return HomeDashVM(mealDao, userDao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}