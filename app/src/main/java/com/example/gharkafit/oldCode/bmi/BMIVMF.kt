package com.example.gharkafit.oldCode.bmi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gharkafit.data.user.UserDao

class BMIVMF(
    private val userDao: UserDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(BMIVM::class.java)) {
            return BMIVM(userDao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}