package com.example.gharkafit.model

data class OnboardingUiState(
    val name: String = "",
    val age: String = "",
    val height: String = "",
    val weight: String = "",
    val gender: Gender = Gender.MALE,
    val goal: Goal = Goal.WEIGHT_LOSS,
    val activityLevel: ActivityLevel = ActivityLevel.MODERATE,
    val dietHabit: DietHabit = DietHabit.HOME
)