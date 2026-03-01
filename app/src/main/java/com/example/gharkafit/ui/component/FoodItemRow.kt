package com.example.gharkafit.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gharkafit.model.FoodItem

@Composable
fun FoodItemRow(
    food: FoodItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val bgColor =
        if (isSelected)
            MaterialTheme.colorScheme.primaryContainer
        else
            MaterialTheme.colorScheme.surface

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },

        colors = CardDefaults.cardColors(
            containerColor = bgColor
        )
    ) {

        Column(
            modifier = Modifier.padding(12.dp)
        ) {

            Text(
                text = food.name,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "${food.calories} kcal • ${food.protein} g protein",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}