package com.example.gharkafit.ui.screen.activityLevel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gharkafit.data.user.UserDao
import kotlinx.coroutines.launch

class ActivityLevelsVM(
    private val userDao: UserDao
) : ViewModel() {

    fun saveActivity(level: String) {

        viewModelScope.launch {

            val user = userDao.getUser()

            val updated = user.copy(
                activityLevel = level
            )

            userDao.updateUser(updated)
        }
    }
}