package com.example.gharkafit


data class DailyFeedback(
    val title: String,
    val message: String,
    val status: FeedbackStatus
)



enum class FeedbackStatus {
    NEEDS_IMPROVEMENT,
    GOOD_PROGRESS,
    ON_TRACK
}



object FeedbackEngine {

    fun generateDailyFeedback(
        caloriesTaken: Int,
        calorieTarget: Int,
        proteinTaken: Double,
        proteinTarget: Double
    ): DailyFeedback {

        val caloriePercent = (caloriesTaken.toDouble() / calorieTarget) * 100
        val proteinPercent = (proteinTaken / proteinTarget) * 100

        return when {

            // 🟥 Beginner / Unhealthy Phase
            proteinPercent < 50 -> {
                DailyFeedback(
                    title = "Easy start 😊",
                    message =
                        "Aaj protein thoda kam raha, koi tension nahi. " +
                                "Kal sirf ek simple cheez add karo jaise doodh ya dal.",
                    status = FeedbackStatus.NEEDS_IMPROVEMENT
                )
            }

            // 🟨 Improving Phase
            proteinPercent in 50.0..80.0 -> {
                DailyFeedback(
                    title = "Good progress 👍",
                    message =
                        "Protein intake improve ho raha hai. " +
                                "Isi consistency ke saath continue rakho.",
                    status = FeedbackStatus.GOOD_PROGRESS
                )
            }

            // 🟩 On Track Phase
            else -> {
                DailyFeedback(
                    title = "Great job 💪",
                    message =
                        "Aaj tumne apna protein target almost hit kar liya. " +
                                "Body ko sahi nutrition mil raha hai!",
                    status = FeedbackStatus.ON_TRACK
                )
            }
        }
    }

    /* -------- Weekly Summary Feedback (future ready) -------- */

    fun generateWeeklyFeedback(
        averageProteinPercent: Double
    ): String {
        return when {
            averageProteinPercent < 60 ->
                "Is week dheere-dheere improve hua hai. Next week aur better kar sakte ho 💪"

            averageProteinPercent in 60.0..85.0 ->
                "Good consistency 👏 is week tum kaafi disciplined rahe."

            else ->
                "Excellent work 🔥 tumhari diet routine kaafi strong ho rahi hai!"
        }
    }
}
