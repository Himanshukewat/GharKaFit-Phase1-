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

@Composable
fun MainApp() {
    val context = LocalContext.current
    val database = remember {
        MainDatabase.getDatabase(context)
    }
    val repository = remember { UserRepository(database.userDao()) }
    var startKey by remember { mutableStateOf<Any?>(null) }
//    val backStack = remember { mutableStateListOf<Any>(WelcomeKey) }

    LaunchedEffect(Unit) {
        val user = repository.getUser()
        startKey =
            if (user == null)
                WelcomeKey
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
                        val repository = remember { AuthRepository() }
                        val factory = remember { AuthViewModelFactory(repository) }
                        val viewModel: AuthViewModel = viewModel(factory = factory)

                        LoginScreen(
                            email = viewModel.uiState.email,
                            password = viewModel.uiState.password,
                            isLoading = viewModel.uiState.isLoading,
                            error = viewModel.uiState.error,

                            onEmailChange = viewModel::updateEmail,
                            onPasswordChange = viewModel::updatePassword,

                            onLoginClick = {
                                Log.d("MAINAPP", "Login Click")
                                viewModel.login {
                                    Log.d("MAINAPP", "Navigation")
                                    backStack.clear()
                                    backStack.add(OnboardingKey)
                                }
                            },
                            onForgotPasswordClick = {

                            },
                            onSignupClick = {
                                backStack.add(SignupKey)
                            }
                        )
                    }

                    is SignupKey -> NavEntry(key) {
                        val repository = remember { AuthRepository() }
                        val factory = remember { AuthViewModelFactory(repository) }
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