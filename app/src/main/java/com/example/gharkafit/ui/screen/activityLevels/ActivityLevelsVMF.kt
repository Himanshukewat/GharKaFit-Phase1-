package com.example.gharkafit.ui.screen.activityLevel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gharkafit.data.user.UserDao

class ActivityLevelsVMF(
    private val userDao: UserDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ActivityLevelsVM::class.java)) {
            return ActivityLevelsVM(userDao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }
}