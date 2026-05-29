package com.example.gharkafit.ui.screen.dietHabit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gharkafit.data.user.UserDao

class DietHabitVMF(
    private val userDao: UserDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(DietHabitVM::class.java)) {

            return DietHabitVM(userDao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}