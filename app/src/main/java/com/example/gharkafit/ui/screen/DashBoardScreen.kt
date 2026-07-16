package com.example.gharkafit.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gharkafit.data.MainDatabase
import com.example.gharkafit.data.food.FoodRepository
import com.example.gharkafit.data.meal.MealLogEntity
import com.example.gharkafit.data.meal.MealRepository
import com.example.gharkafit.ui.component.DashboardCard
import com.example.gharkafit.ui.component.MealStatusItem
import com.example.gharkafit.ui.component.ProgressCard
import com.example.gharkafit.ui.component.RemainingProgress
import com.example.gharkafit.ui.theme.GharKaFitTheme
import com.example.gharkafit.viewmodel.MealViewModel
import com.example.gharkafit.viewmodel.MealViewModelFactory
import java.util.Calendar

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    userName: String,
    caloriesConsumed: Int,
    caloriesTarget: Int,
    proteinConsumed: Int,
    proteinTarget: Int,
    dailyTip: String,
    onAddMealClick: () -> Unit,
    onViewProgressClick: () -> Unit,
    onProfileClick: () -> Unit
) {

    val context = LocalContext.current

    val database = remember { MainDatabase.getDatabase(context) }
    val mealRepository = remember { MealRepository(database.mealDao()) }
    val foodRepository = remember { FoodRepository(database.foodDao()) }

    val factory = remember { MealViewModelFactory(
            mealRepository,
            foodRepository
        )
    }

    val viewModel: MealViewModel = viewModel( factory = factory)
    var consumedCalories by remember { mutableIntStateOf(caloriesConsumed) }
    var consumedProtein by remember { mutableDoubleStateOf(proteinConsumed.toDouble()) }

    val caloriesLeft = caloriesTarget - caloriesConsumed
    val proteinLeft = proteinTarget - proteinConsumed
    val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    var meals by remember { mutableStateOf<List<MealLogEntity>>(emptyList()) }
    var breakfastDone by remember { mutableStateOf(false) }
    var lunchDone by remember { mutableStateOf(false) }
    var dinnerDone by remember { mutableStateOf(false) }

    val greeting = when {
        hour < 12 -> "Good Morning"
        hour < 17 -> "Good Afternoon"
        else -> "Good Evening"
    }

    LaunchedEffect(Unit) {
        viewModel.getTotalCalories {
            consumedCalories = it
        }
        viewModel.getTotalProtein {
            consumedProtein = it
        }
        viewModel.getAllMeals {
            meals = it
        }
        viewModel.hasMeal("Breakfast") {
            breakfastDone = it
        }

        viewModel.hasMeal("Lunch") {
            lunchDone = it
        }

        viewModel.hasMeal("Dinner") {
            dinnerDone = it
        }
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "$greeting $userName 👋",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        text = "Let's stay consistent today",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(
                    onClick = onProfileClick
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Profile"
                    )
                }

            }
        }

        item {

            DashboardCard(
                title = "Today's Progress"
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ProgressCard(
                        modifier = Modifier.weight(1f),
                        title = "Calories",
                        current = consumedCalories,
                        target = caloriesTarget
                    )
                    ProgressCard(
                        modifier = Modifier.weight(1f),
                        title = "Protein",
                        current = consumedProtein.toInt(),
                        target = proteinTarget
                    )
                }
            }
        }

        item {

            DashboardCard(
                title = "Quick Actions"
            ) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Button(
                        onClick = { onAddMealClick() }, modifier = Modifier.weight(1f)
                    ) {
                        Text("Add Meal")
                    }

                    Button(
                        onClick = { onViewProgressClick() }, modifier = Modifier.weight(1f)
                    ) {
                        Text("View Progress")
                    }
                }
            }
        }

        item {
            DashboardCard(
                title = "Today's Meals"
            ) {
                MealStatusItem(
                    mealName = "🍳 Breakfast",
                    isAdded = breakfastDone
                )

                MealStatusItem(
                    mealName = "🍛 Lunch",
                    isAdded = lunchDone
                )

                MealStatusItem(
                    mealName = "🌙 Dinner",
                    isAdded = dinnerDone
                )
            }
        }
        item {

            DashboardCard(
                title = "Remaining Targets"
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    RemainingProgress(
                        modifier = Modifier.weight(1f),
                        title = "Calories Left",
                        value = "$caloriesLeft kcal"
                    )

                    RemainingProgress(
                        modifier = Modifier.weight(1f),
                        title = "Protein Left",
                        value = "$proteinLeft g"
                    )
                }


                Spacer(
                    modifier = Modifier.height(12.dp)
                )
            }
        }
        item {
            DashboardCard(
                title = "Daily Tip"
            ) {

                Text(
                    text = dailyTip, style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

