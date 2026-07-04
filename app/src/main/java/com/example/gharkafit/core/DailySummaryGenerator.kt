package com.example.gharkafit.core

data class DailySummary(
    val strength: String,
    val improvement: String,
    val tomorrowFocus: String
)


// for daily summary dynamic
class DailySummaryGenerator {
    fun generate(
        calories: Int,
        calorieTarget: Int?,
        protein: Double,
        proteinTarget: Double?
    ): DailySummary {
        var strength = ""
        var improvement = ""
        var tomorrowFocus = ""

        if (calories <= calorieTarget ?: 0) {
            strength = "You stayed within your calorie target today."
        } else {
            strength = "You tracked your meals today."
        }

        if (protein >= (proteinTarget?:0.0) * 0.8) {
            improvement = "Your protein intake was good."
        } else {
            improvement = "Increase your protein intake."
        }

        if (protein < proteinTarget ?: 0.0) {
            tomorrowFocus = "Include paneer, milk, curd or soy chunks."
        } else {
            tomorrowFocus = "Maintain the same consistency tomorrow."
        }

        return DailySummary(
            strength = strength,
            improvement = improvement,
            tomorrowFocus = tomorrowFocus
        )
    }
}