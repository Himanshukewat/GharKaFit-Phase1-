package com.example.gharkafit.ui

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

@Composable
fun MainApp() {

    val backStack = remember {
        mutableStateListOf<Any>(WelcomeKey)
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
                                backStack.add(OnboardingKey)
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
                            recommendedWeight = "58kg - 68kg",
                            calories = user.targetCalories,
                            protein = user.targetProtein.toInt(),
                            suggestions = listOf(
                                "Increase protein intake",
                                "Stay hydrated"
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

                    else -> NavEntry(Unit) { Text("Unknown Screen") }
                }
            }
        )
    }
}