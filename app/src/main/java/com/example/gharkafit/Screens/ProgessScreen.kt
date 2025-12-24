package com.example.gharkafit.Screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProgressInsightScreen(
    currentBmi: Double,
    weeklyProteinAvg: Double,
    weeklyCalorieAvg: Int,
    calorieTarget: Int
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = "Your Progress & Insights 📈",
            style = MaterialTheme.typography.headlineSmall
        )

        // 🔹 BODY INSIGHT
        InsightCard(
            title = "Body Insight",
            content = """
                Current BMI: ${String.format("%.1f", currentBmi)}
                Healthy BMI Range: 18.5 – 24.9
                
                This is a reference range, not a pressure target.
            """.trimIndent()
        )

        // 🔹 WEEKLY NUTRITION
        InsightCard(
            title = "Weekly Nutrition Summary",
            content = """
                Avg Calories: $weeklyCalorieAvg kcal
                Avg Protein: ${weeklyProteinAvg.toInt()} g
                
                Consistency matters more than perfection 👍
            """.trimIndent()
        )

        // 🔹 CALORIE CONTROL
        val controlMessage =
            if (weeklyCalorieAvg <= calorieTarget)
                "Good calorie control this week 👏"
            else
                "Calories thode zyada rahe, next week better kar sakte ho 😊"

        InsightCard(
            title = "Calorie Control",
            content = controlMessage
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "You’re building habits, not chasing numbers 💪",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}


@Composable
fun InsightCard(
    title: String,
    content: String
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Text(content, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
