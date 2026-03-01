package com.example.gharkafit.ui.screen.homeDash

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gharkafit.data.MainDatabase
import com.example.gharkafit.ui.component.ProgressCard
import com.example.gharkafit.ui.component.FeedbackCard

@Composable
fun HomeScreenDash(
    onAddFoodClick: () -> Unit,
    onViewProgressClick: () -> Unit
) {

    val context = LocalContext.current
    val db = MainDatabase.getDatabase(context)

    val viewModel: HomeDashVM = viewModel(
        factory = HomeDashVMF(db.mealDao(), db.userDao())
    )

    val calories by viewModel.calories.collectAsState()
    val protein by viewModel.protein.collectAsState()

    val calorieTarget by viewModel.calorieTarget.collectAsState()
    val proteinTarget by viewModel.proteinTarget.collectAsState()

    val feedback by viewModel.feedback.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = "Today's Progress",
            style = MaterialTheme.typography.headlineSmall
        )

        ProgressCard(
            title = "Calories",
            taken = calories,
            target = calorieTarget,
            unit = "kcal"
        )

        ProgressCard(
            title = "Protein",
            taken = protein.toInt(),
            target = proteinTarget,
            unit = "g"
        )

        FeedbackCard(
            feedback = feedback
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onAddFoodClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Food")
        }

        Button(
            onClick = onViewProgressClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View Progress")
        }
    }
}