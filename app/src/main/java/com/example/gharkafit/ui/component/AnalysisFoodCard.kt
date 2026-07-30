package com.example.gharkafit.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AnalysisFoodCard(
    title: String,
    calories: String,
    protein: String,
    carbs: String,
    fat: String,
    insight: String
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.outlineVariant
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "🤖 HearthDiet AI",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )


            Text("Calories : $calories")
            Text("Protein : $protein")
            Text("Carbs : $carbs")
            Text("Fat : $fat")

            HorizontalDivider()

            Text(
                text = "💡 AI Insight",
                style = MaterialTheme.typography.titleSmall
            )

            Text(
                text = insight,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}