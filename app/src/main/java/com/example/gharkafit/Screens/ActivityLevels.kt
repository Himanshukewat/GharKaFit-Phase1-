package com.example.gharkafit.Screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gharkafit.core.ActivityLevel



@Composable
fun ActivityLevels(
    onContinue: (ActivityLevel) -> Unit
) {

    var selectedActivity by remember { mutableStateOf<ActivityLevel?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = "Your Activity Routine",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "This helps us calculate your daily calories correctly.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        ActivityCard(
            title = "Sedentary",
            description = "No workout, mostly sitting or desk work",
            isSelected = selectedActivity == ActivityLevel.SEDENTARY
        ) {
            selectedActivity = ActivityLevel.SEDENTARY
        }

        ActivityCard(
            title = "Gym-going",
            description = "Workout 3–5 days a week",
            isSelected = selectedActivity == ActivityLevel.MODERATE
        ) {
            selectedActivity = ActivityLevel.MODERATE
        }

        ActivityCard(
            title = "Active Lifestyle",
            description = "Physical work, sports, or intense daily activity",
            isSelected = selectedActivity == ActivityLevel.ACTIVE
        ) {
            selectedActivity = ActivityLevel.ACTIVE
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                selectedActivity?.let { onContinue(it) }
            },
            enabled = selectedActivity != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continue")
        }
    }
}


@Composable
fun ActivityCard(
    title: String,
    description: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val bgColor = if (isSelected) Color(0xFFE8F5E9) else Color.White
    val borderColor = if (isSelected) Color(0xFF4CAF50) else Color.LightGray

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        border = CardDefaults.outlinedCardBorder().copy(
            width = 1.dp,
            brush = androidx.compose.ui.graphics.SolidColor(borderColor)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
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
fun ActivityRoutineScreenPreview() {
    ActivityLevels(
        onContinue = { /* preview ke liye empty */ }
    )
}
