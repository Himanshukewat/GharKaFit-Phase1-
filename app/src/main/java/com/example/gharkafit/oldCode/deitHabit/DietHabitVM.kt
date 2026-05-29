package com.example.gharkafit.ui.screen.dietHabit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gharkafit.data.user.UserDao
import kotlinx.coroutines.launch

class DietHabitVM(
    private val userDao: UserDao
) : ViewModel() {

    fun saveHabit(habit: String) {

        viewModelScope.launch {

            val user = userDao.getUser()

            val updated = user.copy(
                dietHabit = habit
            )

            userDao.updateUser(updated)
        }
    }
}