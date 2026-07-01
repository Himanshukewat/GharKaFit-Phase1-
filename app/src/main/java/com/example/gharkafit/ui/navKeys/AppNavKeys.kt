package com.example.gharkafit.ui.navKeys

import com.example.gharkafit.data.user.UserEntity

object WelcomeKey

object OnboardingKey

data class HealthAnalysisKey(
    val user: UserEntity
)
data class PersonalizedPlanKey(
    val user: UserEntity
)

data class DashboardKey(
    val user: UserEntity
)

object MealInsightsKey

object ProgressKey

//object UserDetailKey
//
//object ActivityLevelKey
//
//object GoalSelectionKey
//
//object DietHabitKey
//
//object BMIScreenKey
//
//object ResultBMIKey
//
//object HomeDashKey
//
//object FoodPickerKey
//
//object DailySummaryKey
//
//object ProgressKey
