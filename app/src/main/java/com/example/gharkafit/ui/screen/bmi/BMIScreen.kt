package com.example.gharkafit.ui.screen.bmi

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gharkafit.core.Calculator
import com.example.gharkafit.core.bmiMessage
import com.example.gharkafit.data.MainDatabase
import com.example.gharkafit.ui.component.ResultCard

@Composable
fun BMIScreen() {

    val context = LocalContext.current
    val db = MainDatabase.getDatabase(context)

    val viewModel: BMIVM = viewModel(
        factory = BMIVMF(db.userDao())
    )

    val user by viewModel.user.collectAsState()

    if (user == null) return

    val bmi = Calculator.calculateBMI(
        weightKg = user!!.weightKg,
        heightCm = user!!.heightCm
    )

    val calories = Calculator.calculateDailyCalories(user!!)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

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
            value = "$calories kcal",
            subtitle = "Based on your body & activity level"
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {

            Text(
                text = "Understanding your body comes first. Next we’ll personalize it based on your goal 😊",
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Choose My Goal")
        }
    }
}