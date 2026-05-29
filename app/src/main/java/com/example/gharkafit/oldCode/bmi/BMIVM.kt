package com.example.gharkafit.oldCode.bmi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gharkafit.data.user.UserDao
import com.example.gharkafit.data.user.UserEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BMIVM(
    private val userDao: UserDao
) : ViewModel() {

    private val _user = MutableStateFlow<UserEntity?>(null)
    val user: StateFlow<UserEntity?> = _user

    init {
        viewModelScope.launch {
            _user.value = userDao.getUser()
        }
    }
}