package com.example.gharkafit.Screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gharkafit.Calculator
import com.gharkafit.core.*


/* -------------------- ENUM -------------------- */



/* -------------------- MAIN SCREEN -------------------- */

@Composable
fun ResultBMI(
    userProfile: UserProfile,
    dietHabit: DietHabit,
    onStartTracking: () -> Unit
) {

    val bmi = Calculator.calculateBMI(
        weightKg = userProfile.weightKg,
        heightCm = userProfile.heightCm
    )

    val calories = Calculator.calculateDailyCalories(userProfile)
    val protein = Calculator.calculateDailyProtein(userProfile)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Your GharkaFit Plan 🏠💪",
            style = MaterialTheme.typography.headlineSmall
        )

        ResultCard(
            title = "BMI",
            value = String.format("%.1f", bmi),
            subtitle = bmiMessage(bmi)
        )

        ResultCard(
            title = "Daily Calories",
            value = "$calories kcal",
            subtitle = "Based on your goal & activity"
        )

        ResultCard(
            title = "Daily Protein",
            value = "${protein.toInt()} g",
            subtitle = proteinMessage(dietHabit)
        )

        MotivationCard(dietHabit)

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onStartTracking,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Start Daily Tracking")
        }
    }
}

/* -------------------- UI COMPONENTS -------------------- */

@Composable
fun ResultCard(
    title: String,
    value: String,
    subtitle: String
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Text(text = value, style = MaterialTheme.typography.headlineSmall)
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun MotivationCard(dietHabit: DietHabit) {
    val message = when (dietHabit) {
        DietHabit.PROCESSED ->
            "We’ll start easy and improve step by step 😊 No pressure."

        DietHabit.MIXED ->
            "Good base 👍 We’ll fine-tune your meals gradually."

        DietHabit.HOME ->
            "Great! Ghar ka khana hi real strength hai 💪"
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = message,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

/* -------------------- HELPERS -------------------- */

fun bmiMessage(bmi: Double): String {
    return when {
        bmi < 18.5 -> "Underweight"
        bmi < 25 -> "Normal weight"
        bmi < 30 -> "Overweight"
        else -> "Obese"
    }
}

fun proteinMessage(dietHabit: DietHabit): String {
    return when (dietHabit) {
        DietHabit.PROCESSED ->
            "We’ll gradually improve your protein intake."

        DietHabit.MIXED ->
            "Nice balance! Let’s optimize protein."

        DietHabit.HOME ->
            "Strong base! Just maintain consistency."
    }
}

/* -------------------- PREVIEW -------------------- */

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    ResultBMI(
        userProfile = UserProfile(
            age = 22,
            heightCm = 167.0,
            weightKg = 64.0,
            gender = Gender.MALE,
            activityLevel = ActivityLevel.MODERATE,
            goal = Goal.FAT_LOSS
        ),
        dietHabit = DietHabit.PROCESSED,
        onStartTracking = {}
    )
}
