package com.example.gharkafit.ui.screen.progress

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gharkafit.data.MainDatabase
import com.example.gharkafit.ui.component.InsightCard

@Composable
fun ProgressScreen() {

    val context = LocalContext.current
    val db = MainDatabase.getDatabase(context)

    val viewModel: ProgressVM = viewModel(
        factory = ProgressVMF(db.mealDao(), db.userDao())
    )

    val bmi by viewModel.bmi.collectAsState()
    val weeklyCalories by viewModel.weeklyCalories.collectAsState()
    val weeklyProtein by viewModel.weeklyProtein.collectAsState()
    val calorieTarget by viewModel.calorieTarget.collectAsState()

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

        InsightCard(
            title = "Body Insight",
            content = """
                Current BMI: ${String.format("%.1f", bmi)}
                Healthy BMI Range: 18.5 – 24.9
                
                This is a reference range, not a pressure target.
            """.trimIndent()
        )

        InsightCard(
            title = "Weekly Nutrition Summary",
            content = """
                Avg Calories: $weeklyCalories kcal
                Avg Protein: ${weeklyProtein.toInt()} g
                
                Consistency matters more than perfection 👍
            """.trimIndent()
        )

        val controlMessage =
            if (weeklyCalories <= calorieTarget)
                "Good calorie control this week 👏"
            else
                "Calories thode zyada rahe, next week better kar sakte ho 😊"

        InsightCard(
            title = "Calorie Control",
            content = controlMessage
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "You're building habits, not chasing numbers 💪",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}