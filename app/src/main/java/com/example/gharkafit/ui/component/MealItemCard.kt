package com.example.gharkafit.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MealStatusItem(
    mealName: String,
    isAdded: Boolean
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = mealName,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text =
                if (isAdded)
                    "✔ Added"
                else
                    "⏳ Pending",
            color =
                if (isAdded)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.error
        )
    }
}