package com.example.gharkafit.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gharkafit.ui.component.AnalysisFoodCard
import com.example.gharkafit.ui.component.DailySummaryCard
import com.example.gharkafit.ui.component.MealInputBar
import com.example.gharkafit.ui.component.SuggestionCard
import com.example.gharkafit.ui.component.SummaryCard
import androidx.compose.ui.tooling.preview.Preview
import com.example.gharkafit.ui.component.UserMessageBubble
import com.example.gharkafit.ui.theme.GharKaFitTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import com.example.gharkafit.data.MainDatabase
import com.example.gharkafit.data.food.FoodRepository
import com.example.gharkafit.data.meal.MealLogEntity
import com.example.gharkafit.data.meal.MealRepository
import com.example.gharkafit.viewmodel.MealViewModel
import com.example.gharkafit.viewmodel.MealViewModelFactory


data class MealAnalysis(
    val title: String,
    val calories: String,
    val protein: String,
    val carbs: String,
    val fat: String,
    val insight: String
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealInsightScreen(
    modifier: Modifier = Modifier,
    userMessages: List<String>,
    analyses: List<MealAnalysis>,
    summaryCalories: String,
    summaryProtein: String,
    summaryCarbs: String,
    summaryFat: String,
    suggestions: List<String>,
    totalCalories: String,
    totalProtein: String,
    strength: String,
    improvement: String,
    tomorrowFocus: String
) {

    val context = LocalContext.current

    val database = remember { MainDatabase.getDatabase(context) }
    val mealRepository = remember { MealRepository(database.mealDao()) }

    val foodRepository = remember { FoodRepository(database.foodDao()) }
    val factory = remember { MealViewModelFactory(mealRepository,foodRepository) }
    val viewModel: MealViewModel = viewModel(factory = factory)

    var mealText by remember { mutableStateOf("") }
    var selectedMealType by remember { mutableStateOf("Breakfast") }
    var messages by remember { mutableStateOf(userMessages) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.onPrimary,

                topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Meal Insights 🍱"
                    )
                }
            )
        },

        bottomBar = {
            MealInputBar(
                selectedMealType = selectedMealType,
                onMealTypeChange = {
                    selectedMealType = it
                },
                mealText = mealText,
                onValueChange = {
                    mealText = it
                },
                onSend = {
                    if (mealText.isNotBlank()) {
                        viewModel.analyzeMeal(mealText) { food ->
//                            Log.d("FOOD_RESULT", food.toString())
                            val meal = MealLogEntity(
                                foodName = mealText,
                                mealType = selectedMealType,
                                quantity = 1.0,
                                date = System.currentTimeMillis().toString(),
                                calories = food?.calories ?: 0,
                                protein = food?.protein ?: 0.0,
                                carbs = food?.carbs ?: 0.0,
                                fat = food?.fat ?: 0.0
                            )
//                            Log.d("MEAL_TO_SAVE", meal.toString())
                            viewModel.saveMeal(meal)
//                            viewModel.checkMeals()
                            messages = messages + mealText
                            mealText = ""
                        }
                    }
                }
            )
        }

    ) { paddingValues ->

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),

            contentPadding = PaddingValues(
                start = 20.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 24.dp
            ),

            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(
                count = messages.size
            ) { index ->
                UserMessageBubble(
                    message = messages[index]
                )
                Spacer( modifier = Modifier.height(8.dp) )
                if (index < analyses.size) {
                    AnalysisFoodCard(
                        title = analyses[index].title,
                        calories = analyses[index].calories,
                        protein = analyses[index].protein,
                        carbs = analyses[index].carbs,
                        fat = analyses[index].fat,
                        insight = analyses[index].insight
                    )
                }
            }

            item {

                SummaryCard(
                    calories = summaryCalories,
                    protein = summaryProtein,
                    carbs = summaryCarbs,
                    fat = summaryFat
                )
            }

            item {

                SuggestionCard(
                    suggestions = suggestions
                )
            }

            item {

                DailySummaryCard(
                    totalCalories = totalCalories,
                    totalProtein = totalProtein,
                    strength = strength,
                    improvement = improvement,
                    tomorrowFocus = tomorrowFocus
                )
            }

            item {

                Spacer(
                    modifier = Modifier.height(20.dp)
                )
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun MealInsightScreenPreview() {
//
//    GharKaFitTheme {
//        MealInsightScreen(
//            userMessages = listOf(
//                "Aaj breakfast me 500ml milk liya",
//                "Lunch me 4 roti aur paneer bhurji li"
//            ),
//            analyses = listOf(
//                MealAnalysis(
//                    title = "🥛 Breakfast Analysis",
//                    calories = "250 kcal",
//                    protein = "16 g",
//                    carbs = "24 g",
//                    fat = "8 g",
//                    insight = "✅ Good protein source"
//                ),
//                MealAnalysis(
//                    title = "🍽 Lunch Analysis",
//                    calories = "700 kcal",
//                    protein = "30 g",
//                    carbs = "75 g",
//                    fat = "25 g",
//                    insight = "⚠ Roti quantity slightly high"
//                )
//            ),
//
//            summaryCalories = "950 kcal",
//            summaryProtein = "46 g",
//            summaryCarbs = "99 g",
//            summaryFat = "33 g",
//
//            suggestions = listOf(
//                "Add salad",
//                "Include protein source",
//                "Keep dinner light"
//            ),
//
//            totalCalories = "2050 kcal",
//            totalProtein = "92 g",
//
//            strength = "Protein target achieved",
//
//            improvement = "Water intake low",
//
//            tomorrowFocus = "Add fruit in breakfast"
//        )
//    }
//}