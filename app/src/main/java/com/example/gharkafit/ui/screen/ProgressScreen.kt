package com.example.gharkafit.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gharkafit.ui.component.ProgressInfoCard
import com.example.gharkafit.ui.component.ProgressStat
import com.example.gharkafit.ui.theme.GharKaFitTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressScreen(
    modifier: Modifier = Modifier,
    goal: String,
    targetWeight: Int,
    currentWeight: Int,
    mealsLogged: Int,
    proteinTargetDays: Int,
    caloriesTargetDays: Int,
    weeklyInsight: String
) {
    val remainingWeight = currentWeight - targetWeight
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Your Progress 📈",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "Track how your habits are improving",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background)
        { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ProgressInfoCard(title = "Current Goal",
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Text(
                        text = goal,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = "Target: $targetWeight kg",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            item { ProgressInfoCard(title = "Weight Progress") {
                    Text(
                        text = "Current Weight: $currentWeight kg",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Text(
                        text = "Goal Weight: $targetWeight kg",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Text(
                        text = "Progress: $remainingWeight kg remaining",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            item { ProgressInfoCard(title = "This Week") {
                    ProgressStat(
                        label = "Meals Logged",
                        value = "$mealsLogged"
                    )

                    ProgressStat(
                        label = "Protein Target Met",
                        value = "$proteinTargetDays days"
                    )

                    ProgressStat(
                        label = "Calories Target Met",
                        value = "$caloriesTargetDays days"
                    )
                }
            }
            item { ProgressInfoCard(title = "Weekly Insight",
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ) {
                    Text(
                        text = weeklyInsight,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            item { ProgressInfoCard(title = "Keep Going 💪",
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                    Text(
                        text = "Small daily habits create long-term results.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProgressScreenPreview() {
    GharKaFitTheme {
        ProgressScreen(
            goal = "Fat Loss",
            targetWeight = 60,
            currentWeight = 65,
            mealsLogged = 12,
            proteinTargetDays = 4,
            caloriesTargetDays = 3,
            weeklyInsight = "You are consistent with protein, but dinner calories are usually high."
        )
    }
}