package com.example.gharkafit.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gharkafit.ui.component.DashboardCard
import com.example.gharkafit.ui.component.MealStatusItem
import com.example.gharkafit.ui.component.ProgressCard
import com.example.gharkafit.ui.component.RemainingProgress
import com.example.gharkafit.ui.theme.GharKaFitTheme
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
    breakfastAdded: Boolean,
    lunchAdded: Boolean,
    dinnerAdded: Boolean,
    onAddMealClick: () -> Unit,
    onViewProgressClick: () -> Unit
) {
    val caloriesLeft = caloriesTarget - caloriesConsumed
    val proteinLeft = proteinTarget - proteinConsumed
    val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

    val greeting = when {
        hour < 12 -> "Good Morning"
        hour < 17 -> "Good Afternoon"
        else -> "Good Evening"
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {

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
                        current = caloriesConsumed,
                        target = caloriesTarget
                    )
                    ProgressCard(
                        modifier = Modifier.weight(1f),
                        title = "Protein",
                        current = proteinConsumed,
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
                    isAdded = breakfastAdded
                )

                MealStatusItem(
                    mealName = "🍛 Lunch",
                    isAdded = lunchAdded
                )

                MealStatusItem(
                    mealName = "🌙 Dinner",
                    isAdded = dinnerAdded
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


@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    GharKaFitTheme {
        DashboardScreen(
            userName = "Himanshu",
            caloriesConsumed = 1200,
            caloriesTarget = 2100,
            proteinConsumed = 55,
            proteinTarget = 95,
            breakfastAdded = true,
            lunchAdded = false,
            dinnerAdded = false,
            dailyTip = "Try to include a protein source in every meal today.",
            onAddMealClick = {},
            onViewProgressClick = {}
        )
    }
}