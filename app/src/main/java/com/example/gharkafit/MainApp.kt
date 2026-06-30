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
import com.example.gharkafit.ui.navKeys.*
import com.example.gharkafit.ui.screen.DashboardScreen
import com.example.gharkafit.ui.screen.HealthAnalysisScreen
import com.example.gharkafit.ui.screen.OnboardingScreen
import com.example.gharkafit.ui.screen.PersonalizedPlanScreen
import com.example.gharkafit.ui.screen.WelcomeScreen
import com.example.gharkafit.ui.screen.MealInsightScreen
import com.example.gharkafit.ui.screen.MealAnalysis
import com.example.gharkafit.ui.screen.ProgressScreen

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
                            onContinueClick = {
                                backStack.add(HealthAnalysisKey)
                            }
                        )
                    }

                    is HealthAnalysisKey -> NavEntry(key) {
                        HealthAnalysisScreen(
                            bmi = 17.4,
                            bmiStatus = "Normal Weight ✅",
                            healthyRange = "18.5 - 24.9",
                            recommendedWeight = "58kg - 68kg",
                            calories = 2400,
                            protein = 95,
                            suggestions = listOf(
                                "Increase protein intake",
                                "Stay hydrated"
                            ),
                            onViewPlanClick = {
                                backStack.add(PersonalizedPlanKey)
                            }
                        )
                    }

                    is PersonalizedPlanKey -> NavEntry(key) {
                        PersonalizedPlanScreen(
                            onStartTrackingClick = {
                                backStack.add(DashboardKey)
                            }
                        )
                    }

                    is DashboardKey -> NavEntry(key) {
                        DashboardScreen(
                            userName = "Himanshu",
                            caloriesConsumed = 1200,
                            caloriesTarget = 2100,
                            proteinConsumed = 55,
                            proteinTarget = 95,
                            dailyTip = "Try to include a protein source in every meal today.",
                            breakfastAdded = true,
                            lunchAdded = false,
                            dinnerAdded = false,
                            onAddMealClick = {
                                backStack.add(MealInsightsKey)
                            },
                            onViewProgressClick = {
                                backStack.add(ProgressKey)
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
                        ProgressScreen(
                            goal = "Fat Loss",
                            targetWeight = 60,
                            currentWeight = 65,
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