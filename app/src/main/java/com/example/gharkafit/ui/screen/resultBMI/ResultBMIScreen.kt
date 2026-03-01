package com.example.gharkafit.ui.screen.resultBMI

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gharkafit.data.MainDatabase
import com.example.gharkafit.core.Calculator
import com.example.gharkafit.ui.component.ResultCard
import com.example.gharkafit.ui.component.MotivationCard

@Composable
fun ResultBMIScreen(
    onStartTracking: () -> Unit
) {

    val context = LocalContext.current
    val db = MainDatabase.getDatabase(context)

    val viewModel: ResultBMIVM = viewModel(
        factory = ResultBMIVMF(db.userDao())
    )

    val user by viewModel.user.collectAsState()

    user?.let {

        val bmi = Calculator.calculateBMI(
            weightKg = it.weightKg,
            heightCm = it.heightCm
        )

        val calories = Calculator.calculateDailyCalories(it)
        val protein = Calculator.calculateDailyProtein(it)

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
                subtitle = viewModel.bmiMessage(bmi)
            )

            ResultCard(
                title = "Daily Calories",
                value = "$calories kcal",
                subtitle = "Based on your goal & activity"
            )

            ResultCard(
                title = "Daily Protein",
                value = "${protein.toInt()} g",
                subtitle = viewModel.proteinMessage(it.dietHabit)
            )

            MotivationCard(it.dietHabit)

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { onStartTracking() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Start Daily Tracking")
            }
        }
    }
}