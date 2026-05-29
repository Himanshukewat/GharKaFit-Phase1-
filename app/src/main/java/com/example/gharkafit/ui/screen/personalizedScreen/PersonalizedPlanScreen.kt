package com.example.gharkafit.ui.screen.personalizedScreen

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gharkafit.ui.component.FoodChip
import com.example.gharkafit.ui.component.PlanCard
import com.example.gharkafit.ui.component.TargetItem
import com.example.gharkafit.ui.theme.GharKaFitTheme

@Composable
fun PersonalizedPlanScreen(
    onStartTrackingClick: ()-> Unit
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
                    text = "Fat Loss",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Lose fat while maintaining strength and energy.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        item {

            PlanCard(
                title = "Daily Targets"
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    TargetItem(
                        label = "Calories", value = "2100 kcal"
                    )

                    TargetItem(
                        label = "Protein", value = "95 g"
                    )

                    TargetItem(
                        label = "Water", value = "3L"
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

                    SuggestionItem("Increase protein intake")
                    SuggestionItem("Walk 8k steps daily")
                    SuggestionItem("Sleep 7–8 hours")
                    SuggestionItem("Reduce sugary drinks")
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

                    FoodChip("Paneer")
                    FoodChip("Dal")
                    FoodChip("Milk")
                    FoodChip("Eggs")
                    FoodChip("Fruits")
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


//@Preview(showBackground = true)
//@Composable
//fun PersonalizedPlanScreenPreview() {
//
//    GharKaFitTheme {
//
//        PersonalizedPlanScreen()
//    }
//}