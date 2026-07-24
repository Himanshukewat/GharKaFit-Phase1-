package com.example.gharkafit.ui.navKeys

import com.example.gharkafit.data.user.UserEntity
import kotlinx.serialization.Serializable

@Serializable
data object WelcomeKey

@Serializable
data object OnboardingKey

@Serializable
data object MealInsightsKey

data class HealthAnalysisKey(
    val user: UserEntity
)
data class PersonalizedPlanKey(
    val user: UserEntity
)

data class DashboardKey(
    val user: UserEntity
)


data class ProgressKey(
    val user: UserEntity
)

@Serializable
data object EditProfileKey

@Serializable
data object ProfileKey

@Serializable
data object LoginKey

@Serializable
data object SignupKey