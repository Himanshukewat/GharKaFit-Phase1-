package com.example.gharkafit.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun MealInputBar(
    selectedMealType: String,
    onMealTypeChange: (String) -> Unit,
    mealText: String,
    onValueChange: (String) -> Unit,
    onSend: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Meal Type",
            style = MaterialTheme.typography.titleSmall
        )

        Spacer(modifier = Modifier.height(4.dp))
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
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                value = mealText,
                onValueChange = onValueChange,
                modifier = Modifier.weight(3f),
                singleLine = true,
                shape = RoundedCornerShape(20.dp),
                placeholder = {
                    Text("Type your meal here...")
                }
            )

            Button(
                onClick = onSend,
                modifier = Modifier
                    .weight(1f),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "Send",
                    style = MaterialTheme.typography.titleSmall
                )
            }

        }
    }
}