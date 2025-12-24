package com.gharkafit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gharkafit.core.Goal

@Composable
fun GoalSelection(
    onGoalSelected: (Goal) -> Unit
) {

    var selectedGoal by remember { mutableStateOf<Goal?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = "Choose Your Goal",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "We’ll personalize your diet step by step.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        GoalCard(
            title = "Fat Loss",
            description = "Lose fat gradually without starving",
            isSelected = selectedGoal == Goal.FAT_LOSS
        ) {
            selectedGoal = Goal.FAT_LOSS
        }

        GoalCard(
            title = "Muscle Gain",
            description = "Build strength with ghar ka khana",
            isSelected = selectedGoal == Goal.MUSCLE_GAIN
        ) {
            selectedGoal = Goal.MUSCLE_GAIN
        }

        GoalCard(
            title = "Stay Fit",
            description = "Maintain a healthy & active lifestyle",
            isSelected = selectedGoal == Goal.MAINTAIN
        ) {
            selectedGoal = Goal.MAINTAIN
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                selectedGoal?.let { onGoalSelected(it) }
            },
            enabled = selectedGoal != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continue")
        }
    }
}

@Composable
fun GoalCard(
    title: String,
    description: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor =
        if (isSelected) Color(0xFFE8F5E9) else Color.White

    val borderColor =
        if (isSelected) Color(0xFF4CAF50) else Color.LightGray

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        border = CardDefaults.outlinedCardBorder().copy(
            width = 1.dp,
            brush = androidx.compose.ui.graphics.SolidColor(borderColor)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GoalSelectionScreenPreview() {
    GoalSelection(
        onGoalSelected = { /* Preview ke liye empty */ }
    )
}

