package com.example.gharkafit.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MotivationCard(
    dietHabit: String
) {

    val message = when (dietHabit) {

        "PROCESSED" ->
            "We’ll start easy and improve step by step 😊"

        "MIXED" ->
            "Good base 👍 We'll fine-tune meals gradually"

        else ->
            "Great! Ghar ka khana is real strength 💪"
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = message,
            modifier = Modifier.padding(16.dp)
        )
    }
}