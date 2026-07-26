package com.example.gharkafit.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MealTypeSelector(
    selectedMealType: String,
    onMealTypeChange: (String) -> Unit
) {

    Text(
        text = "Meal Type",
        style = MaterialTheme.typography.titleSmall
    )

    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        listOf(
            "Breakfast",
            "Lunch",
            "Dinner",
            "Snack"
        ).forEach { type ->
            FilterChip(
                selected = selectedMealType == type,
                onClick = {
                    onMealTypeChange(type)
                },
                label = {
                    Text(type)
                }
            )
        }
    }
}