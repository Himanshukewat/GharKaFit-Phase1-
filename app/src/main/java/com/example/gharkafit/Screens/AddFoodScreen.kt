package com.example.gharkafit.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

import com.example.gharkafit.IndianFoodData
import com.example.gharkafit.FoodItem

@Composable
fun AddFoodScreen(
    onFoodAdded: (FoodItem, Int) -> Unit
) {

    var selectedFood by remember { mutableStateOf<FoodItem?>(null) }
    var quantity by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Add Food 🍲",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Select a food item",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(IndianFoodData.foodList) { food ->
                FoodItemRow(
                    food = food,
                    isSelected = selectedFood == food,
                    onClick = { selectedFood = food }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        QuantitySelector(
            quantity = quantity,
            onIncrease = { quantity++ },
            onDecrease = { if (quantity > 1) quantity-- }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                selectedFood?.let {
                    onFoodAdded(it, quantity)
                }
            },
            enabled = selectedFood != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add to Today")
        }
    }
}


@Composable
fun FoodItemRow(
    food: FoodItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val bgColor =
        if (isSelected) Color(0xFFE8F5E9) else Color.White

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = bgColor)
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
                color = Color.Gray
            )
        }
    }
}

@Composable
fun QuantitySelector(
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = "Quantity",
            style = MaterialTheme.typography.titleMedium
        )

        Row(verticalAlignment = Alignment.CenterVertically) {

            OutlinedButton(onClick = onDecrease) {
                Text("-")
            }

            Text(
                text = quantity.toString(),
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.titleMedium
            )

            OutlinedButton(onClick = onIncrease) {
                Text("+")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddFoodScreenPreview() {
    AddFoodScreen(
        onFoodAdded = { _, _ ->
            // preview ke liye empty
        }
    )
}
