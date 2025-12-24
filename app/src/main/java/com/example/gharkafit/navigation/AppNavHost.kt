package com.example.gharkafit.navigation

import androidx.compose.runtime.*
import androidx.navigation.compose.*
import androidx.navigation.NavHostController

import com.example.gharkafit.*
import com.example.gharkafit.Screens.*
import com.gharkafit.GoalSelection
import com.gharkafit.core.UserProfile
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController



@Composable
fun AppNavHost(navController: NavHostController) {

    var userProfile by remember { mutableStateOf<UserProfile?>(null) }
    var dietHabit by remember { mutableStateOf<DietHabit?>(null) }
    var totalCalories by remember { mutableStateOf(0) }
    var totalProtein by remember { mutableStateOf(0.0) }
    var dailyFeedback by remember {
        mutableStateOf(
            DailyFeedback(
                title = "Getting started 💪",
                message = "Aaj apna pehla meal log karo.",
                status = FeedbackStatus.ON_TRACK
            )
        )
    }



    NavHost(
        navController = navController,
        startDestination = NavRoutes.UserDetail.route
    ) {

        composable(NavRoutes.UserDetail.route) {
            UserDetailScreen { profile ->
                userProfile = profile
                navController.navigate(NavRoutes.BMI.route)
            }
        }

        composable(NavRoutes.BMI.route) {
            BMIScreen(
                userProfile = userProfile!!,
                onContinue = { navController.navigate(NavRoutes.Goal.route) }
            )
        }

        composable(NavRoutes.AddFood.route) {
            AddFoodScreen { food, quantity ->

                totalCalories += food.calories * quantity
                totalProtein += food.protein * quantity

                // 🔥 SIMPLE FEEDBACK LOGIC (abhi)
                dailyFeedback =
                    if (totalProtein >= Calculator.calculateDailyProtein(userProfile!!)) {
                        DailyFeedback(
                            title = "Great job 👏",
                            message = "Aaj ka protein target almost complete ho gaya!",
                            status = FeedbackStatus.ON_TRACK
                        )
                    } else {
                        DailyFeedback(
                            title = "Good progress 👍",
                            message = "Protein intake improve ho raha hai, thoda aur focus karo.",
                            status = FeedbackStatus.GOOD_PROGRESS
                        )
                    }

                navController.navigate(NavRoutes.DailySummary.route) {
                    popUpTo(NavRoutes.AddFood.route) {
                        inclusive = true   // 🔥 AddFood REMOVE
                    }
                }
            }
        }




        composable(NavRoutes.Goal.route) {
            GoalSelection { goal ->
                userProfile = userProfile!!.copy(goal = goal)
                navController.navigate(NavRoutes.Activity.route)
            }
        }

        composable(NavRoutes.Activity.route) {
            ActivityLevels { activity ->
                userProfile = userProfile!!.copy(activityLevel = activity)
                navController.navigate(NavRoutes.Diet.route)
            }
        }

        composable(NavRoutes.Diet.route) {
            DietHabbit { habit ->
                dietHabit = habit
                navController.navigate(NavRoutes.ResultBMI.route)
            }
        }

        composable(NavRoutes.ResultBMI.route) {
            ResultBMI(
                userProfile = userProfile!!,
                dietHabit = dietHabit!!,
                onStartTracking = {
                    navController.navigate(NavRoutes.Home.route)
                }
            )
        }
        composable(NavRoutes.DailySummary.route) {
            DailySummaryScreen(
                caloriesTaken = totalCalories,
                calorieTarget = Calculator.calculateDailyCalories(userProfile!!),
                proteinTaken = totalProtein,
                proteinTarget = Calculator.calculateDailyProtein(userProfile!!),
                dailyFeedback = dailyFeedback,   // ✅ OBJECT, NOT STRING
                onDone = {
                    navController.navigate(NavRoutes.Home.route) {
                        popUpTo(NavRoutes.Home.route) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                }

            )
        }

        composable(NavRoutes.Proges.route) {
            ProgressInsightScreen(
                currentBmi = Calculator.calculateBMI(
                    userProfile!!.weightKg,
                    userProfile!!.heightCm
                ),
                weeklyCalorieAvg = totalCalories,
                weeklyProteinAvg = totalProtein,
                calorieTarget = Calculator.calculateDailyCalories(userProfile!!)
            )
        }





        composable(NavRoutes.Home.route) {
            HomeScreenDash(
                caloriesTaken = totalCalories,
                calorieTarget = Calculator.calculateDailyCalories(userProfile!!),
                proteinTaken = totalProtein,
                proteinTarget = Calculator.calculateDailyProtein(userProfile!!),
                feedbackStatus = FeedbackStatus.GOOD_PROGRESS,
                feedbackMessage = "Keep going 💪",
                onAddFoodClick = {
                    navController.navigate(NavRoutes.AddFood.route)
                },
                onViewProgressClick = {
                    navController.navigate(NavRoutes.Proges.route) // 👈 YEH LINE
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppNavHostPreview() {
    val navController = rememberNavController()
    AppNavHost(navController = navController)
}
