package com.example.gharkafit.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gharkafit.DailyFeedback
import com.example.gharkafit.FeedbackStatus


@Composable
fun DailySummaryScreen(
    caloriesTaken: Int,
    calorieTarget: Int,
    proteinTaken: Double,
    proteinTarget: Double,
    dailyFeedback: DailyFeedback,
    onDone: () -> Unit
)
 {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = "Today's Summary 📊",
            style = MaterialTheme.typography.headlineSmall
        )

        SummaryCard(
            title = "Calories",
            taken = caloriesTaken,
            target = calorieTarget,
            unit = "kcal"
        )

        SummaryCard(
            title = "Protein",
            taken = proteinTaken.toInt(),
            target = proteinTarget.toInt(),
            unit = "g"
        )

        FeedbackCard(
            feedback = dailyFeedback
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onDone,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Done for Today")
        }
    }
}

/* ---------------- SUMMARY CARD ---------------- */

@Composable
fun SummaryCard(
    title: String,
    taken: Int,
    target: Int,
    unit: String
) {
    val progress =
        if (target > 0) (taken.toFloat() / target).coerceIn(0f, 1f)
        else 0f

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(title, style = MaterialTheme.typography.titleMedium)

            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier.fillMaxWidth()
            )

            Text("$taken / $target $unit")
        }
    }
}

/* ---------------- FEEDBACK CARD ---------------- */

@Composable
fun FeedbackCard(
    feedback: DailyFeedback
) {

    val bgColor = when (feedback.status) {
        FeedbackStatus.NEEDS_IMPROVEMENT -> Color(0xFFFFF3E0)
        FeedbackStatus.GOOD_PROGRESS -> Color(0xFFFFFDE7)
        FeedbackStatus.ON_TRACK -> Color(0xFFE8F5E9)
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = bgColor),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = feedback.title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = feedback.message,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

// only for previww


@Preview(showBackground = true)
@Composable
fun DailySummaryScreenPreview() {

    val feedback = DailyFeedback(
        title = "Good progress 👍",
        message = "Protein intake improve ho raha hai. Isi consistency ko banaye rakho.",
        status = FeedbackStatus.GOOD_PROGRESS
    )

    DailySummaryScreen(
        caloriesTaken = 1600,
        calorieTarget = 2000,
        proteinTaken = 65.0,
        proteinTarget = 90.0,
        dailyFeedback = feedback,
        onDone = {}
    )
}
