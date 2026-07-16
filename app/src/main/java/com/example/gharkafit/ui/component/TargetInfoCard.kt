package com.example.gharkafit.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TargetInfoCard(
    calories: String,
    protein: String,
    targetWeight: String,
    water: String
) {
    ProfileSectionCard(
        title = "📊 Updated Targets"
    ) {
        Text(
            text = "Auto updates while editing",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProfileInfo(
            icon = "🔥",
            title = "Calories Target",
            value = calories
        )
        Spacer(modifier = Modifier.height(10.dp))
        ProfileInfo(
            icon = "💪",
            title = "Protein Target",
            value = protein
        )
        Spacer(modifier = Modifier.height(10.dp))
        ProfileInfo(
            icon = "🎯",
            title = "Target Weight",
            value = targetWeight
        )
        Spacer(modifier = Modifier.height(10.dp))
        ProfileInfo(
            icon = "💧",
            title = "Water Target",
            value = water
        )
    }
}