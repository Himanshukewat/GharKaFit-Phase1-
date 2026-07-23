package com.example.gharkafit.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.example.gharkafit.ui.component.AnalysisCard
import kotlinx.coroutines.NonCancellable.key

@Composable
fun HealthAnalysisScreen(
    bmi: Double,
    bmiStatus: String,
    healthyRange: String,
    currentWeight: Double,
    recommendedWeight: String,
    calories: Int,
    protein: Int,
    suggestions: List<String>,
    onViewPlanClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = "Your Health Analysis 💪",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Understand your current body and fitness status",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        item {

            AnalysisCard(
                title = "Current BMI"
            ) {

                Text(
                    text = String.format("%.1f", bmi),
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold,
                    color =  MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = bmiStatus,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        item {

            AnalysisCard(
                title = "Healthy BMI Range"
            ) {

                Text(
                    text = healthyRange,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "This is considered the healthy range for most adults.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        item {

            AnalysisCard(
                title = "Recommended Weight Range"
            ) {
                Text(
                    text = recommendedWeight,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Current Weight : ${currentWeight.toInt()} kg",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Healthy range based on your height.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        item {

            AnalysisCard(
                title = "Daily Nutrition Needs"
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Column {

                        Text(
                            text = "Calories",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Text(
                            text = "$calories kcal",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Column {

                        Text(
                            text = "Protein",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Text(
                            text = "$protein g",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        item {
            AnalysisCard(
                title = "Quick Suggestions"
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    suggestions.forEach {
                        SuggestionItem(it)
                    }
                }
            }
        }


        // BUTTON

        item {

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                        onViewPlanClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor =  MaterialTheme.colorScheme.primary
                )
            ) {

                Text(
                    text = "View My Plan"
                )
            }
        }
    }
}
