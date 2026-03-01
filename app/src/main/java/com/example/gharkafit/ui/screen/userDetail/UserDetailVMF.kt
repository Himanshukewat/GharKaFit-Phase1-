package com.example.gharkafit.ui.screen.userDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gharkafit.data.user.UserDao

class UserDetailVMF(
    private val userDao: UserDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(UserDetailVM::class.java)) {

            return UserDetailVM(userDao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}