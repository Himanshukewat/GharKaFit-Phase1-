package com.example.gharkafit.Screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class DietHabit {
    PROCESSED,
    MIXED,
    HOME
}

@Composable
fun DietHabbit(
    onContinue: (DietHabit) -> Unit
) {

    var selectedHabit by remember { mutableStateOf<DietHabit?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = "Your Current Diet",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "No judgment. We’ll improve step by step 😊",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        DietHabitCard(
            title = "Mostly Processed Food",
            description = "Fast food, packaged snacks, sugary drinks",
            isSelected = selectedHabit == DietHabit.PROCESSED
        ) {
            selectedHabit = DietHabit.PROCESSED
        }

        DietHabitCard(
            title = "Mixed Diet",
            description = "Some home food, some outside food",
            isSelected = selectedHabit == DietHabit.MIXED
        ) {
            selectedHabit = DietHabit.MIXED
        }

        DietHabitCard(
            title = "Mostly Home Food",
            description = "Roti, sabzi, dal, rice, ghar ka khana",
            isSelected = selectedHabit == DietHabit.HOME
        ) {
            selectedHabit = DietHabit.HOME
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                selectedHabit?.let { onContinue(it) }
            },
            enabled = selectedHabit != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("See My Plan")
        }
    }
}

@Composable
fun DietHabitCard(
    title: String,
    description: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val bgColor = if (isSelected) Color(0xFFE8F5E9) else Color.White
    val borderColor = if (isSelected) Color(0xFF4CAF50) else Color.LightGray

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        border = CardDefaults.outlinedCardBorder().copy(
            width = 1.dp,
            brush = androidx.compose.ui.graphics.SolidColor(borderColor)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DietHabitsScreenPreview() {
    DietHabbit(
        onContinue = { /* preview ke liye empty */ }
    )
}
