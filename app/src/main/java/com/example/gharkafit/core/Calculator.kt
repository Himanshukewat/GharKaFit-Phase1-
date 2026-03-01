package com.example.gharkafit.core

import com.example.gharkafit.data.user.UserEntity

object Calculator {

    fun calculateBMI(weightKg: Double, heightCm: Double): Double {
        val heightM = heightCm / 100
        return weightKg / (heightM * heightM)
    }

    fun calculateDailyCalories(user: UserEntity): Int {

        val bmr = (10 * user.weightKg +
                6.25 * user.heightCm -
                5 * user.age + 5)

        val activityMultiplier = when (user.activityLevel) {
            "SEDENTARY" -> 1.2
            "MODERATE" -> 1.55
            "ACTIVE" -> 1.75
            else -> 1.2
        }

        return (bmr * activityMultiplier).toInt()
    }

    fun calculateDailyProtein(user: UserEntity): Double {

        val weight = user.weightKg

        val multiplier = when (user.goal) {

            "FAT_LOSS" -> 1.6

            "MUSCLE_GAIN" -> 2.0

            "MAINTAIN" -> 1.2

            else -> 1.2
        }

        return weight * multiplier
    }
}