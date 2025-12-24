package com.example.gharkafit.Screens



import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gharkafit.FeedbackStatus

@Composable
fun HomeScreenDash(
    caloriesTaken: Int,
    calorieTarget: Int,
    proteinTaken: Double,
    proteinTarget: Double,
    feedbackStatus: FeedbackStatus,
    feedbackMessage: String,
    onAddFoodClick: () -> Unit,
    onViewProgressClick: () -> Unit   // 👈 NEW
)
{

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = "Today’s Progress",
            style = MaterialTheme.typography.headlineSmall
        )

        ProgressCard(
            title = "Calories",
            taken = caloriesTaken,
            target = calorieTarget,
            unit = "kcal"
        )

        ProgressCard(
            title = "Protein",
            taken = proteinTaken.toInt(),
            target = proteinTarget.toInt(),
            unit = "g"
        )

        FeedbackCard(
            status = feedbackStatus,
            message = feedbackMessage
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


@Composable
fun ProgressCard(
    title: String,
    taken: Int,
    target: Int,
    unit: String
) {
    val progress = (taken.toFloat() / target).coerceIn(0f, 1f)

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium)

            LinearProgressIndicator(
                progress = progress,
                color = Color(0xFF4CAF50),
                trackColor = Color(0xFFE0E0E0),
                modifier = Modifier.fillMaxWidth()
            )

            Text("$taken / $target $unit")
        }
    }
}



@Composable
fun FeedbackCard(
    status: FeedbackStatus,
    message: String
) {
    val bgColor = when (status) {
        FeedbackStatus.NEEDS_IMPROVEMENT -> Color(0xFFFFF3E0)
        FeedbackStatus.GOOD_PROGRESS -> Color(0xFFFFFDE7)
        FeedbackStatus.ON_TRACK -> Color(0xFFE8F5E9)
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = bgColor),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = message,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


//Example only for preview
//@Preview(showBackground = true)
//@Composable
//fun HomeDashboardScreenPreview() {
//    HomeScreenDash (
//        caloriesTaken = 1200,
//        calorieTarget = 2000,
//        proteinTaken = 55.0,
//        proteinTarget = 90.0,
//        feedbackStatus = FeedbackStatus.GOOD_PROGRESS,
//        feedbackMessage = "Good going! Thoda aur protein add kar lo 💪",
//        onAddFoodClick = {}
//    )
//}

