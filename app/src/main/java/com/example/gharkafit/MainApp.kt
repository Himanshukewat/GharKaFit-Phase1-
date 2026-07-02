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
                            userName = user.name,
                            caloriesConsumed = 0,
                            caloriesTarget = user.targetCalories,
                            proteinConsumed = 0,
                            proteinTarget = user.targetProtein.toInt(),
                            dailyTip = "Try to include a protein source in every meal today.",
                            onAddMealClick = {
                                backStack.add(MealInsightsKey)
                            },
                            onViewProgressClick = {
                                backStack.add(
                                    ProgressKey(user)
                                )
                            }
                        )
                    }

                    is MealInsightsKey -> NavEntry(key) {
                        MealInsightScreen(
                            userMessages = listOf(
                                "Aaj breakfast me 500ml milk liya",
                                "Lunch me 4 roti aur paneer bhurji li"
                            ),

                            analyses = listOf(
                                MealAnalysis(
                                    title = "🥛 Breakfast Analysis",
                                    calories = "250 kcal",
                                    protein = "16 g",
                                    carbs = "24 g",
                                    fat = "8 g",
                                    insight = "✅ Good protein source"
                                ),
                                MealAnalysis(
                                    title = "🍽 Lunch Analysis",
                                    calories = "700 kcal",
                                    protein = "30 g",
                                    carbs = "75 g",
                                    fat = "25 g",
                                    insight = "⚠ Roti quantity slightly high"
                                )
                            ),

                            summaryCalories = "950 kcal",
                            summaryProtein = "46 g",
                            summaryCarbs = "99 g",
                            summaryFat = "33 g",

                            suggestions = listOf(
                                "Add salad",
                                "Include protein source",
                                "Keep dinner light"
                            ),

                            totalCalories = "2050 kcal",
                            totalProtein = "92 g",

                            strength = "Protein target achieved",
                            improvement = "Water intake low",
                            tomorrowFocus = "Add fruit in breakfast"
                        )
                    }

                    is ProgressKey -> NavEntry(key) {
                        val user = key.user
                        ProgressScreen(
                            goal = user.goal.replace("_", " "),
                            targetWeight = 60,
                            currentWeight = user.weightKg.toInt(),
                            mealsLogged = 12,
                            proteinTargetDays = 4,
                            caloriesTargetDays = 3,
                            weeklyInsight = "You are consistent with protein, but dinner calories are usually high."
                        )
                    }

                    else -> NavEntry(Unit) { Text("Unknown Screen") }
                }
            }
        )
    }
}