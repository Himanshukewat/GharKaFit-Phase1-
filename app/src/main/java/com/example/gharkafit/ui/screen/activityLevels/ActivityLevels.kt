package com.example.gharkafit.ui.screen.activityLevels

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gharkafit.data.MainDatabase
import com.example.gharkafit.model.ActivityLevel
import com.example.gharkafit.ui.component.ActivityCard
import com.example.gharkafit.ui.screen.activityLevel.ActivityLevelsVM
import com.example.gharkafit.ui.screen.activityLevel.ActivityLevelsVMF

@Composable
fun ActivityLevels(
    onContinue: () -> Unit
) {

    val context = LocalContext.current
    val db = MainDatabase.getDatabase(context)

    val viewModel: ActivityLevelsVM = viewModel(
        factory = ActivityLevelsVMF(db.userDao())
    )

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
                selectedActivity?.let {
                    viewModel.saveActivity(it.name)
                }
                onContinue()
            },
            enabled = selectedActivity != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continue")
        }
    }
}