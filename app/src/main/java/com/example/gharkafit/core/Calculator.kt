package com.example.gharkafit.core

import com.example.gharkafit.data.user.UserEntity

object Calculator {
    fun calculateBMI(weightKg: Double, heightCm: Double): Double {
        val heightM = heightCm / 100
        return weightKg / (heightM * heightM)
    }
    fun calculateDailyCalories(user: UserEntity): Int {
        // BMR (gender based)
        val bmr = if (user.gender == "MALE") {
            10 * user.weightKg +
                    6.25 * user.heightCm -
                    5 * user.age + 5
        } else {
            10 * user.weightKg +
                    6.25 * user.heightCm -
                    5 * user.age - 161
        }

        // Activity multiplier
        val activityMultiplier = when (user.activityLevel) {
            "SEDENTARY" -> 1.2
            "MODERATE" -> 1.55
            "ACTIVE" -> 1.75
            else -> 1.2
        }
        val maintenanceCalories = bmr * activityMultiplier
        //Goal adjustment
        val finalCalories = when (user.goal) {
            "FAT_LOSS" -> maintenanceCalories - 400   // deficit
            "MUSCLE_GAIN" -> maintenanceCalories + 250 // surplus
            "MAINTAIN" -> maintenanceCalories
            else -> maintenanceCalories
        }
        return finalCalories.toInt()
    }

    fun calculateDailyProtein(user: UserEntity): Double {
        val weight = user.weightKg
        val multiplier = when(user.goal){
            "FAT_LOSS" -> 1.6
            "MUSCLE_GAIN" -> 1.8
            "MAINTAIN" -> 1.3
            else -> 1.3
        }
        return weight * multiplier
    }
}