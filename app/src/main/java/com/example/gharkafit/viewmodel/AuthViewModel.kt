package com.example.gharkafit.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gharkafit.auth.AuthRepository
import com.example.gharkafit.data.meal.MealRepository
import com.example.gharkafit.data.remote.FirestoreRepository
import com.example.gharkafit.data.remote.MealFirestoreRepository
import com.example.gharkafit.data.user.UserRepository
import com.example.gharkafit.model.AuthUiState
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository,
    private val userRepository: UserRepository,
    private val firestoreRepository: FirestoreRepository,
    private val mealRepository: MealRepository,
    private val mealFirestoreRepository: MealFirestoreRepository
) : ViewModel() {

    var uiState by mutableStateOf(AuthUiState())
        private set

    fun updateEmail(email: String) {
        uiState = uiState.copy(email = email)
    }

    fun updatePassword(password: String) {
        uiState = uiState.copy(password = password)
    }

    fun updateConfirmPassword(confirmPassword: String) {
        uiState = uiState.copy(confirmPassword = confirmPassword)
    }

    fun signUp(
        onSuccess: () -> Unit
    ) {
        if (uiState.email.isBlank()) {
            uiState = uiState.copy(error = "Please enter email")
            return
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(uiState.email).matches()) {
            uiState = uiState.copy(error = "Enter a valid email")
            return
        }
        if (uiState.password.isBlank()) {
            uiState = uiState.copy(error = "Please enter password")
            return
        }
        if (uiState.confirmPassword.isBlank()) {
            uiState = uiState.copy(error = "Please confirm password")
            return
        }
        if (uiState.password != uiState.confirmPassword) {
            uiState = uiState.copy(error = "Passwords do not match")
            return
        }
        if (uiState.password.length < 6) {
            uiState = uiState.copy(error = "Password must be at least 6 characters")
            return
        }
        uiState = uiState.copy(
            isLoading = true,
            error = null
        )
        repository.signUp(
            email = uiState.email,
            password = uiState.password,
            onSuccess = {
                uiState = uiState.copy(
                    isLoading = false
                )
                onSuccess()
            },
            onError = { message ->
                uiState = uiState.copy(
                    isLoading = false,
                    error = message
                )
            }
        )
    }

    fun login(
        onSuccess: () -> Unit
    ) {
        Log.d("LOGIN", "Login button clicked")

        if (uiState.email.isBlank()) {
            uiState = uiState.copy(error = "Please enter email")
            return
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(uiState.email).matches()) {
            uiState = uiState.copy(error = "Enter a valid email")
            return
        }
        if (uiState.password.isBlank()) {
            uiState = uiState.copy(error = "Please enter password")
            return
        }
        if (uiState.password.length < 6) {
            uiState = uiState.copy(error = "Password must be at least 6 characters")
            return
        }
        uiState = uiState.copy(
            isLoading = true,
            error = null
        )
        Log.d("LOGIN", "Calling Firebase")
        repository.login(
            email = uiState.email.trim(),
            password = uiState.password,
            onSuccess = {
                Log.d("LOGIN", "Login Success")
                val uid = repository.getUid()
                firestoreRepository.getUser(
                    uid = uid,
                    onSuccess = { user ->
                        if (user != null) {
                            viewModelScope.launch {
                                // 1. User Room me save
                                userRepository.replaceUser(user)
                                // 2. Fir Meals fetch karo
                                mealFirestoreRepository.getMeals(
                                    uid = uid,
                                    onSuccess = { meals ->
                                        Log.d("MEAL_FETCH", "Fetched ${meals.size} meals")

                                        viewModelScope.launch {
                                            // 3. Meals Room me save
                                            mealRepository.replaceMeals(meals)
                                            Log.d("MEAL_FETCH", "Meals saved in Room")

                                            // 4. Ab Dashboard kholo
                                            uiState = uiState.copy(
                                                isLoading = false
                                            )
                                            onSuccess()
                                        }
                                    },
                                    onError = { message ->
                                        Log.e("MEAL_FETCH", message)

                                        uiState = uiState.copy(
                                            isLoading = false,
                                            error = message
                                        )
                                    }
                                )
                            }

                        } else {

                            uiState = uiState.copy(
                                isLoading = false
                            )

                            onSuccess()
                        }
                    },
                    onError = { message ->

                        uiState = uiState.copy(
                            isLoading = false,
                            error = message
                        )
                    }
                )
            },
            onError = { message ->
                Log.d("LOGIN", "Login Error: $message")

                uiState = uiState.copy(
                    isLoading = false,
                    error = message
                )
            }
        )
    }

    fun forgotPassword() {
        if (uiState.email.isBlank()) {
            uiState = uiState.copy(error = "Please enter email")
            return
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(uiState.email).matches()) {
            uiState = uiState.copy(error = "Enter a valid email")
            return
        }
        uiState = uiState.copy(
            isLoading = true,
            error = null
        )
        repository.sendPasswordReset(
            email = uiState.email.trim(),
            onSuccess = {
                uiState = uiState.copy(
                    isLoading = false,
                    error = "Password reset email sent."
                )
            },
            onError = { message ->
                uiState = uiState.copy(
                    isLoading = false,
                    error = message
                )
            }
        )
    }
}