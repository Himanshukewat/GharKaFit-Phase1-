package com.example.gharkafit.oldCode.goalSelection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gharkafit.data.user.UserDao
import kotlinx.coroutines.launch

class GoalSelectionVM(
    private val userDao: UserDao
) : ViewModel() {

    fun saveGoal(goal: String) {

        viewModelScope.launch {

            val user = userDao.getUser()

            val updated = user.copy(
                goal = goal
            )

            userDao.updateUser(updated)
        }
    }
}