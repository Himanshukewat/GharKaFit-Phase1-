package com.example.gharkafit.ui.screen.resultBMI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gharkafit.data.user.UserDao

class ResultBMIVMF(
    private val userDao: UserDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ResultBMIVM::class.java)) {

            return ResultBMIVM(userDao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}