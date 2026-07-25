package com.example.gharkafit.ui

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.gharkafit.core.Calculator
import com.example.gharkafit.ui.navKeys.*
import com.example.gharkafit.ui.screen.DashboardScreen
import com.example.gharkafit.ui.screen.HealthAnalysisScreen
import com.example.gharkafit.ui.screen.OnboardingScreen
import com.example.gharkafit.ui.screen.PersonalizedPlanScreen
import com.example.gharkafit.ui.screen.WelcomeScreen
import com.example.gharkafit.ui.screen.MealInsightScreen
import com.example.gharkafit.ui.screen.MealAnalysis
import com.example.gharkafit.ui.screen.ProgressScreen
import com.example.gharkafit.core.bmiMessage
import com.example.gharkafit.ui.screen.EditProfileScreen
import com.example.gharkafit.ui.screen.ProfileScreen
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gharkafit.auth.AuthRepository
import com.example.gharkafit.data.MainDatabase
import com.example.gharkafit.data.user.UserRepository
import com.example.gharkafit.ui.screen.LoginScreen
import com.example.gharkafit.ui.screen.SignupScreen
import com.example.gharkafit.viewmodel.AuthViewModel
import com.example.gharkafit.viewmodel.AuthViewModelFactory
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
import com.example.gharkafit.data.meal.MealRepository
import com.example.gharkafit.data.remote.FirestoreRepository
import com.example.gharkafit.data.remote.MealFirestoreRepository

@Composable
fun MainApp() {
    val context = LocalContext.current
    val database = remember {
        MainDatabase.getDatabase(context)
    }
    val userRepository = remember { UserRepository(database.userDao()) }
    val authRepository = remember { AuthRepository() }
    val firestoreRepository = remember { FirestoreRepository() }
    val mealFirestoreRepository = remember { MealFirestoreRepository() }
    val mealRepository = remember { MealRepository(database.mealDao()) }

    val factory = remember {
        AuthViewModelFactory(
            authRepository,
            userRepository,
            firestoreRepository,
            mealRepository,
            mealFirestoreRepository
        )
    }

    val viewModel: AuthViewModel = viewModel(factory = factory)
    var startKey by remember { mutableStateOf<Any?>(null) }
//    val backStack = remember { mutableStateListOf<Any>(WelcomeKey) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {

        if (!authRepository.isLoggedIn()) {
            startKey = WelcomeKey
            return@LaunchedEffect
        }

        val user = userRepository.getUser()

        startKey =
            if (user == null)
                OnboardingKey
            else
                DashboardKey(user)
    }
    if (startKey == null) {
        androidx.compose.material3.CircularProgressIndicator()
        return
    }

    val backStack = remember(startKey) {
        mutableStateListOf(startKey!!)
    }

    Scaffold { paddingValues ->

        NavDisplay(
            modifier = Modifier.padding(paddingValues),

            backStack = backStack,

            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),

            onBack = { backStack.removeLastOrNull() },

            entryProvider = { key ->

                when (key) {
                     WelcomeKey -> NavEntry(key) {
                        WelcomeScreen(
                            onStartClick = {
                                backStack.add(LoginKey)
                            }
                        )
                    }

                    is OnboardingKey -> NavEntry(key) {
                        OnboardingScreen(
                            onContinueClick = { user ->
                                backStack.add(
                                    HealthAnalysisKey(user)
                                )
                            }
                        )
                    }

                    is HealthAnalysisKey -> NavEntry(key) {
                        val user = key.user
                        val bmi = Calculator.calculateBMI(
                            weightKg = user.weightKg,
                            heightCm = user.heightCm
                        )
                        val bmiStatus = bmiMessage(bmi)
                        HealthAnalysisScreen(
                            bmi = bmi,
                            bmiStatus = bmiStatus,
                            healthyRange = "18.5 - 24.9",
                            currentWeight = user.weightKg,
                            recommendedWeight = Calculator.calculateRecommendedWeight(
                                user.heightCm
                            ),
                            calories = user.targetCalories,
                            protein = user.targetProtein.toInt(),
                            suggestions = Calculator.getSuggestions(
                                bmi = bmi,
                                goal = user.goal
                            ),
                            onViewPlanClick = {
                                backStack.add(PersonalizedPlanKey(user))
                            }
                        )
                    }

                    is PersonalizedPlanKey -> NavEntry(key) {
                        val user = key.user
                        PersonalizedPlanScreen(
                            user = key.user,
                            onStartTrackingClick = {
                                backStack.add(
                                    DashboardKey(user)
                                )
                            },
                        )
                    }

                    is DashboardKey -> NavEntry(key) {
                        val user = key.user
                        DashboardScreen(
                            caloriesConsumed = 0,
                            proteinConsumed = 0,
                            dailyTip = "Try to include a protein source in every meal today.",
                            onAddMealClick = {
                                backStack.add(MealInsightsKey)
                            },
                            onViewProgressClick = {
                                backStack.add(
                                    ProgressKey(user)
                                )
                            },
                            onProfileClick = {
                                backStack.add(ProfileKey)
                            }
                        )
                    }

                    is MealInsightsKey -> NavEntry(key) {
                        MealInsightScreen(
                            suggestions = listOf(
                                "Add salad",
                                "Include protein source",
                                "Keep dinner light"
                            ),
                        )
                    }

                    is ProgressKey -> NavEntry(key) {
                        ProgressScreen(
                            modifier = Modifier
                        )
                    }

                    is ProfileKey -> NavEntry(key) {
                        ProfileScreen(
                            onEditProfile = {
                                backStack.add(EditProfileKey)
                            },
                            onLogout = {
                                authRepository.logout()
                                scope.launch {
//                                    userRepository.deleteAllUsers()
                                    backStack.clear()
                                    backStack.add(WelcomeKey)
                                }

                            }
                        )
                    }

                    is EditProfileKey -> NavEntry(key) {
                        EditProfileScreen(
                            onSave = {
                                backStack.removeAt(backStack.lastIndex)
                            }

                        )
                    }

                    is LoginKey -> NavEntry(key) {
                        val factory = remember {
                            AuthViewModelFactory(
                                authRepository,
                                userRepository,
                                firestoreRepository,
                                mealRepository,
                                mealFirestoreRepository
                            )
                        }
                        val viewModel: AuthViewModel = viewModel(factory = factory)

                        LoginScreen(
                            email = viewModel.uiState.email,
                            password = viewModel.uiState.password,
                            isLoading = viewModel.uiState.isLoading,
                            error = viewModel.uiState.error,

                            onEmailChange = viewModel::updateEmail,
                            onPasswordChange = viewModel::updatePassword,

                            onLoginClick = {
                                viewModel.login {
                                    scope.launch {
                                        val user = userRepository.getUser()
                                        backStack.clear()
                                        if (user == null) {
                                            backStack.add(OnboardingKey)
                                        } else {
                                            backStack.add(DashboardKey(user))
                                        }
                                    }
                                }
                            },
                            onForgotPasswordClick = {
                                viewModel.forgotPassword()
                            },
                            onSignupClick = {
                                backStack.add(SignupKey)
                            }
                        )
                    }

                    is SignupKey -> NavEntry(key) {
                        val factory = remember {
                            AuthViewModelFactory(
                                authRepository,
                                userRepository,
                                firestoreRepository,
                                mealRepository,
                                mealFirestoreRepository
                            )
                        }
                        val viewModel: AuthViewModel = viewModel(factory = factory)
                        SignupScreen(
                            email = viewModel.uiState.email,
                            password = viewModel.uiState.password,
                            confirmPassword = viewModel.uiState.confirmPassword,
                            isLoading = viewModel.uiState.isLoading,
                            error = viewModel.uiState.error,

                            onEmailChange = viewModel::updateEmail,
                            onPasswordChange = viewModel::updatePassword,
                            onConfirmPasswordChange = viewModel::updateConfirmPassword,

                            onSignupClick = {
                                viewModel.signUp {
                                    backStack.add(OnboardingKey)
                                }
                            },

                            onLoginClick = {
                                backStack.removeAt(backStack.lastIndex)
                            }
                        )
                    }
                    else -> NavEntry(Unit) { Text("Unknown Screen") }
                }
            }
        )
    }
}