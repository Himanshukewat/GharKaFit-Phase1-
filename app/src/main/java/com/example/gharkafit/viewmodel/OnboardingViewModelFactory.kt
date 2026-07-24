package com.example.gharkafit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gharkafit.auth.AuthRepository
import com.example.gharkafit.data.user.UserRepository

class OnboardingViewModelFactory(
    private val repository: UserRepository,
    private val authRepository: AuthRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(OnboardingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OnboardingViewModel(repository, authRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}