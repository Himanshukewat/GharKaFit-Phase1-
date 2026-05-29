package com.example.gharkafit.oldCode.goalSelection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gharkafit.data.user.UserDao

class GoalSelectionVMF(
    private val userDao: UserDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(GoalSelectionVM::class.java)) {

            return GoalSelectionVM(userDao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}