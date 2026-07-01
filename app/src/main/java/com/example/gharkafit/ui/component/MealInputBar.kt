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

@Composable
fun MealInputBar(
    mealText: String,
    onValueChange: (String) -> Unit,
    onSend: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
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