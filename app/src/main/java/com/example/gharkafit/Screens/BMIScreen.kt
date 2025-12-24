package com.example.gharkafit.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gharkafit.Calculator
import com.gharkafit.core.UserProfile   // ✅ correct import

@Composable
fun BMIScreen(
    userProfile: UserProfile,
    onContinue: () -> Unit
) {

    val bmi = Calculator.calculateBMI(
        weightKg = userProfile.weightKg,
        heightCm = userProfile.heightCm
    )

    val estimatedCalories = Calculator.calculateDailyCalories(userProfile)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = "Your Body Overview",
            style = MaterialTheme.typography.headlineSmall
        )

        ResultCard(
            title = "BMI",
            value = String.format("%.1f", bmi),
            subtitle = bmiMessage(bmi)
        )

        ResultCard(
            title = "Estimated Calories",
            value = "$estimatedCalories kcal",
            subtitle = "Based on your body & activity level"
        )

        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Understanding your body comes first. Next, we’ll personalize this based on your goal 😊",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onContinue,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Choose My Goal")
        }
    }
}
