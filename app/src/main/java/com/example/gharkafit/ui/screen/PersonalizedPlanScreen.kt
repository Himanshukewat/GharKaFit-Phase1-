package com.example.gharkafit.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.gharkafit.core.Calculator
import com.example.gharkafit.data.user.UserEntity
import com.example.gharkafit.ui.component.FoodChip
import com.example.gharkafit.ui.component.PlanCard
import com.example.gharkafit.ui.component.TargetItem

@Composable
fun PersonalizedPlanScreen(
    user: UserEntity,
    onStartTrackingClick: () -> Unit
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = "Your Personalized Plan 🔥",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "A simple plan based on your body and fitness goal",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        item {

            PlanCard(
                title = "Your Goal"
            ) {
                Text(
                    text = user.goal.replace("_", " ")
                    .lowercase()
                    .replaceFirstChar { it.uppercase() }
                    ?: "--",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = Calculator.getGoalDescription(user.goal),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        item {

            PlanCard(
                title = "Today's Targets"
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    TargetItem(
                        label = "Calories", value =  "${user.targetCalories} kcal"
                    )

                    TargetItem(
                        label = "Protein", value = "${user.targetProtein.toInt()} g"
                    )

                    TargetItem(
                        label = "Water", value = "4L"
                    )
                }
            }
        }

        item {

            PlanCard(
                title = "Focus Areas"
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Calculator.getFocusAreas(user.goal).forEach {
                        SuggestionItem(it)
                    }
                }
            }
        }


        item {

            PlanCard(
                title = "Foods To Focus On"
            ) {

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Calculator.getFoods(user.goal).forEach {
                        FoodChip(it)
                    }
                }
            }
        }

        item {

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                        onStartTrackingClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {

                Text(
                    text = "Start Tracking"
                )
            }
        }
    }
}


@Composable
fun SuggestionItem(
    text: String
) {

    Text(
        text = "✔ $text", style = MaterialTheme.typography.bodyLarge
    )
}

