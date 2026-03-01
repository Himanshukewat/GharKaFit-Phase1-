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
import com.example.gharkafit.ui.screen.activityLevels.ActivityLevels
import com.example.gharkafit.ui.screen.bmi.BMIScreen
import com.example.gharkafit.ui.screen.dailySummary.DailySummaryScreen
import com.example.gharkafit.ui.screen.dietHabit.DietHabit
import com.example.gharkafit.ui.screen.foodPicker.FoodPickerScreen
import com.example.gharkafit.ui.screen.goalSelection.GoalSelection
import com.example.gharkafit.ui.screen.homeDash.HomeScreenDash
import com.example.gharkafit.ui.screen.progress.ProgressScreen
import com.example.gharkafit.ui.screen.resultBMI.ResultBMIScreen
import com.example.gharkafit.ui.screen.userDetail.UserDetail

@Composable
fun MainApp() {

    val backStack = remember {
        mutableStateListOf<Any>(UserDetailKey)
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
                    is UserDetailKey -> NavEntry(key) {
                        UserDetail(

                        )
                    }

                    is ActivityLevelKey -> NavEntry(key) {
                        ActivityLevels(

                        )
                    }

                    is GoalSelectionKey -> NavEntry(key) {
                        GoalSelection(

                        )
                    }

                    is DietHabitKey -> NavEntry(key) {
                        DietHabit(

                        )
                    }

                    is BMIScreenKey -> NavEntry(key) {
                        BMIScreen(

                        )
                    }

                    is ResultBMIKey -> NavEntry(key) {
                        ResultBMIScreen(
                            onStartTracking = {
                                backStack.add(HomeDashKey)
                            }
                        )
                    }

                    is HomeDashKey -> NavEntry(key) {
                        HomeScreenDash(
                            onAddFoodClick = {
                                backStack.add(FoodPickerKey)
                            },
                            onViewProgressClick = {
                                backStack.add(ProgressKey)
                            }
                        )
                    }

                    is FoodPickerKey -> NavEntry(key) {
                        FoodPickerScreen()
                    }

                    is DailySummaryKey -> NavEntry(key) {
                        DailySummaryScreen(

                        )
                    }

                    is ProgressKey -> NavEntry(key) {
                        ProgressScreen()
                    }

                    else -> NavEntry(Unit) { Text("Unknown Screen") }
                }
            }
        )
    }
}