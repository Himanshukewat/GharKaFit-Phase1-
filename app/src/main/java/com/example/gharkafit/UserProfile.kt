package com.gharkafit.core

enum class Gender {
    MALE,
    FEMALE,
    OTHER
}

//enum class ActivityLevel {
//    SEDENTARY,      // no workout
//    LIGHT,          // walk / light activity
//    MODERATE,       // gym 3–4 days
//    ACTIVE          // gym 5–6 days
//}

enum class ActivityLevel {
    SEDENTARY,
    LIGHT,
    MODERATE,
    ACTIVE
}

enum class Goal {
    FAT_LOSS,
    MUSCLE_GAIN,
    MAINTAIN
}

data class UserProfile(
    val age: Int,
    val heightCm: Double,
    val weightKg: Double,
    val gender: Gender,
    val activityLevel: ActivityLevel,
    val goal: Goal
)
