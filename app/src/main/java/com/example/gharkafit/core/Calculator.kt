package com.example.gharkafit.core

object Calculator {

    fun calculateBMI(
        weightKg: Double,
        heightCm: Double
    ): Double {

        val heightM = heightCm / 100
        return weightKg / (heightM * heightM)
    }

    fun calculateDailyCalories(
        weightKg: Double,
        heightCm: Double,
        age: Int,
        gender: String,
        activityLevel: String,
        goal: String
    ): Int {

        // BMR
        val bmr = if (gender == "MALE") {
            10 * weightKg +
                    6.25 * heightCm -
                    5 * age + 5
        } else {
            10 * weightKg +
                    6.25 * heightCm -
                    5 * age - 161
        }

        // Activity Multiplier
        val activityMultiplier = when (activityLevel) {
            "SEDENTARY" -> 1.2
            "MODERATE" -> 1.55
            "ACTIVE" -> 1.75
            else -> 1.2
        }

        val maintenanceCalories = bmr * activityMultiplier

        // Goal Adjustment
        val finalCalories = when (goal) {
            "FAT_LOSS" -> maintenanceCalories - 400
            "MUSCLE_GAIN" -> maintenanceCalories + 250
            "MAINTAIN" -> maintenanceCalories
            else -> maintenanceCalories
        }

        return finalCalories.toInt()
    }

    fun calculateDailyProtein(
        weightKg: Double,
        goal: String
    ): Double {

        val multiplier = when (goal) {
            "FAT_LOSS" -> 1.6
            "MUSCLE_GAIN" -> 1.8
            "MAINTAIN" -> 1.3
            else -> 1.3
        }

        return weightKg * multiplier
    }
}