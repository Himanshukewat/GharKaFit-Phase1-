package com.example.gharkafit.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DailySummaryCard(
    totalCalories: String,
    totalProtein: String,
    strength: String,
    improvement: String,
    tomorrowFocus: String
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Text(
                text = "📅 Daily Summary",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "Total Calories : $totalCalories"
            )

            Text(
                text = "Total Protein : $totalProtein"
            )

            HorizontalDivider()

            Text(
                text = "Strengths",
                style = MaterialTheme.typography.titleSmall
            )

            Text(
                text = "✅ $strength"
            )

            HorizontalDivider()

            Text(
                text = "Improvements",
                style = MaterialTheme.typography.titleSmall
            )

            Text(
                text = "⚠ $improvement"
            )

            HorizontalDivider()

            Text(
                text = "Tomorrow Focus",
                style = MaterialTheme.typography.titleSmall
            )

            Text(
                text = "• $tomorrowFocus"
            )
        }
    }
}