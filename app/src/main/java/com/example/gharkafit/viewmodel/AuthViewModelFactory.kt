package com.example.gharkafit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gharkafit.auth.AuthRepository
import com.example.gharkafit.data.remote.FirestoreRepository
import com.example.gharkafit.data.user.UserRepository

class AuthViewModelFactory(
    private val repository: AuthRepository,
    private val userRepository: UserRepository,
    private val firestoreRepository: FirestoreRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(repository, userRepository , firestoreRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}