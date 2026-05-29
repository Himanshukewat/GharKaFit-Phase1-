package com.example.gharkafit.oldCode.progress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gharkafit.data.meal.MealDao
import com.example.gharkafit.data.user.UserDao

class ProgressVMF(
    private val mealDao: MealDao,
    private val userDao: UserDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ProgressVM::class.java)) {

            return ProgressVM(mealDao, userDao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}