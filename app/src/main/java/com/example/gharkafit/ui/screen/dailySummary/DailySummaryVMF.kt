package com.example.gharkafit.ui.screen.dailySummary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gharkafit.data.meal.MealDao
import com.example.gharkafit.data.user.UserDao

class DailySummaryVMF(
    private val mealDao: MealDao,
    private val userDao: UserDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(DailySummaryVM::class.java)) {

            return DailySummaryVM(mealDao, userDao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }
}