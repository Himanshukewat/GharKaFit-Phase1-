package com.example.gharkafit

import com.gharkafit.core.ActivityLevel
import com.gharkafit.core.Gender
import com.gharkafit.core.Goal
import com.gharkafit.core.UserProfile


object Calculator {

    /* ---------------- BMI ---------------- */


    fun calculateBMI(weightKg: Double, heightCm: Double): Double {
        val heightM = heightCm / 100
        return weightKg / (heightM * heightM)
    }

    /* ---------------- BMR ---------------- */
    // Mifflin-St Jeor Formula (accurate & industry standard)

    fun calculateBMR(user: UserProfile): Double {
        return when (user.gender) {
            Gender.MALE ->
                10 * user.weightKg + 6.25 * user.heightCm - 5 * user.age + 5

            Gender.FEMALE ->
                10 * user.weightKg + 6.25 * user.heightCm - 5 * user.age - 161

            Gender.OTHER ->
                // Neutral average (safe & respectful handling)
                10 * user.weightKg + 6.25 * user.heightCm - 5 * user.age - 78
        }
    }

    /* ---------------- Activity Multiplier ---------------- */

    private fun activityMultiplier(level: ActivityLevel): Double {
        return when (level) {
            ActivityLevel.SEDENTARY -> 1.2
            ActivityLevel.LIGHT -> 1.375
            ActivityLevel.MODERATE -> 1.55
            ActivityLevel.ACTIVE -> 1.725
        }
    }

    /* ---------------- Daily Calories ---------------- */

    fun calculateDailyCalories(user: UserProfile): Int {
        val bmr = calculateBMR(user)
        val maintenanceCalories = bmr * activityMultiplier(user.activityLevel)

        val finalCalories = when (user.goal) {
            Goal.FAT_LOSS -> maintenanceCalories - 300
            Goal.MUSCLE_GAIN -> maintenanceCalories + 300
            Goal.MAINTAIN -> maintenanceCalories
        }

        return finalCalories.toInt()
    }

    /* ---------------- Daily Protein (Gradual Logic) ---------------- */
    // Pressure-free transition system

    fun calculateDailyProtein(user: UserProfile): Double {
        val proteinMultiplier = when (user.goal) {
            Goal.FAT_LOSS -> 1.2       // easy start
            Goal.MUSCLE_GAIN -> 1.6    // gym-friendly
            Goal.MAINTAIN -> 1.0
        }

        return user.weightKg * proteinMultiplier
    }
}
