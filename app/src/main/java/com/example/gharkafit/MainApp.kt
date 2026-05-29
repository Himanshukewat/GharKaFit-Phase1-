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
import com.example.gharkafit.ui.screen.healthAnalysicsScreen.HealthAnalysisScreen
import com.example.gharkafit.ui.screen.onBoardingScreen.OnboardingScreen
import com.example.gharkafit.ui.screen.personalizedScreen.PersonalizedPlanScreen
import com.example.gharkafit.ui.screen.welcomeScreen.WelcomeScreen

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

                    else -> NavEntry(Unit) { Text("Unknown Screen") }
                }
            }
        )
    }
}