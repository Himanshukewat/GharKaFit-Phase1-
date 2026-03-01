package com.example.gharkafit.ui.screen.foodPicker

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gharkafit.data.IndianFoodData
import com.example.gharkafit.model.FoodItem
import com.example.gharkafit.ui.component.FoodItemRow
import com.example.gharkafit.ui.component.QuantitySelector
import com.example.gharkafit.data.MainDatabase

@Composable
fun FoodPickerScreen() {

    val context = LocalContext.current
    val db = MainDatabase.getDatabase(context)

    val viewModel: FoodPickerVM = viewModel(
        factory = FoodPickerVMF(db.mealDao())
    )

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

        LazyColumn(
            modifier = Modifier.weight(1f),
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

                    viewModel.addFood(it, quantity)

                }
            },

            enabled = selectedFood != null,

            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add to Today")
        }
    }
}