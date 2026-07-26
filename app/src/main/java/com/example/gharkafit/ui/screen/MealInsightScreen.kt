package com.example.gharkafit.ui.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gharkafit.auth.AuthRepository
import com.example.gharkafit.core.DateUtils
import com.example.gharkafit.core.MealInsightGenerator
import com.example.gharkafit.data.MainDatabase
import com.example.gharkafit.data.food.FoodRepository
import com.example.gharkafit.data.meal.MealLogEntity
import com.example.gharkafit.data.meal.MealRepository
import com.example.gharkafit.data.remote.FirestoreRepository
import com.example.gharkafit.data.remote.MealFirestoreRepository
import com.example.gharkafit.data.user.UserEntity
import com.example.gharkafit.data.user.UserRepository
import com.example.gharkafit.model.MealAnalysisResult
import com.example.gharkafit.ui.component.AnalysisFoodCard
import com.example.gharkafit.ui.component.DailySummaryCard
import com.example.gharkafit.ui.component.MealInputBar
import com.example.gharkafit.ui.component.MealTypeSelector
import com.example.gharkafit.ui.component.SuggestionCard
import com.example.gharkafit.ui.component.SummaryCard
import com.example.gharkafit.ui.component.UserMessageBubble
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
    suggestions: List<String>,
) {

    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    var mealToDelete by remember {
        mutableStateOf<MealLogEntity?>(null)
    }
// edit dialog
    var showEditDialog by remember {
        mutableStateOf(false)
    }

    var mealToEdit by remember {
        mutableStateOf<MealLogEntity?>(null)
    }

    var editedMealText by remember {
        mutableStateOf("")
    }

    var editedMealType by remember {
        mutableStateOf("Breakfast")
    }

    val context = LocalContext.current

    val database = remember { MainDatabase.getDatabase(context) }
    val mealRepository = remember { MealRepository(database.mealDao()) }
    val userRepository = remember { UserRepository(database.userDao()) }

    val foodRepository = remember { FoodRepository(database.foodDao()) }
    val firestoreRepository = remember { FirestoreRepository() }
    val authRepository = remember { AuthRepository() }
    val mealFirestoreRepository = remember { MealFirestoreRepository() }

    val factory = remember {
        MealViewModelFactory(
            mealRepository,
            foodRepository,
            firestoreRepository,
            mealFirestoreRepository
        )
    }
    val viewModel: MealViewModel = viewModel(factory = factory)
    val insightGenerator = remember { MealInsightGenerator() }

    var user by remember { mutableStateOf<UserEntity?>(null) }
    var mealText by remember { mutableStateOf("") }
    var selectedMealType by remember { mutableStateOf("Breakfast") }
//    var messages by remember { mutableStateOf(userMessages) }
    var meals by remember { mutableStateOf<List<MealLogEntity>>(emptyList()) }
    var totalCaloriesValue by remember { mutableStateOf(0) }
    var totalProteinValue by remember { mutableStateOf(0.0) }
    var totalCarbsValue by remember { mutableStateOf(0.0) }
    var totalFatValue by remember { mutableStateOf(0.0) }

    var isLoading by remember { mutableStateOf(false) }

    val dailySummary = remember(
        totalCaloriesValue,
        totalProteinValue,
        user
    ) {
        viewModel.generateSummary(
            calories = totalCaloriesValue,
            calorieTarget = user?.targetCalories ?: 0,
            protein = totalProteinValue,
            proteinTarget = user?.targetProtein ?: 0.0
        )
    }

    fun refreshNutritionData() {
        viewModel.getTotalCalories {
            totalCaloriesValue = it
        }
        viewModel.getTotalProtein {
            totalProteinValue = it
        }
        viewModel.getTotalCarbs {
            totalCarbsValue = it
        }
        viewModel.getTotalFat {
            totalFatValue = it
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getUser(userRepository) {
            user = it
        }
        viewModel.getMeals {
            meals = it
        }
        refreshNutritionData()
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = {
                showDeleteDialog = false
            },
            title = {
                Text("Delete Meal")
            },
            text = {
                Text("Are you sure you want to delete this meal?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        mealToDelete?.let {
                            viewModel.deleteMeal(it) {
                                viewModel.getMeals {
                                    meals = it
                                }
                                refreshNutritionData()
                            }
                        }
                        showDeleteDialog = false
                    }
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDeleteDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }

    if (showEditDialog) {
        AlertDialog(
            onDismissRequest = {
                showEditDialog = false
            },
            title = {
                Text("Edit Meal")
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = editedMealText,
                        onValueChange = {
                            editedMealText = it
                        },
                        label = {
                            Text("Meal")
                        }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    MealTypeSelector(
                        selectedMealType = editedMealType,
                        onMealTypeChange = {
                            editedMealType = it
                        }
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        mealToEdit?.let { oldMeal ->
                            isLoading = true
                            viewModel.analyzeMealWithAI(
                                input = editedMealText,
                                onResult = { result ->
                                    val updatedMeal = oldMeal.copy(
                                        foodName = result.foodName,
                                        mealType = editedMealType,
                                        quantity = result.quantity,
                                        calories = result.calories,
                                        protein = result.protein,
                                        carbs = result.carbs,
                                        fat = result.fat
                                    )
                                    viewModel.updateMeal(updatedMeal) {
                                        viewModel.getMeals {
                                            meals = it
                                        }
                                        refreshNutritionData()
                                        isLoading = false
                                        showEditDialog = false
                                    }
                                },
                                onError = { error ->
                                    isLoading = false
                                    Toast.makeText(
                                        context,
                                        error,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            )
                        }
                    },
                    enabled = !isLoading
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text("Save")
                    }
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showEditDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }

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
                isLoading = isLoading,
                onMealTypeChange = {
                    selectedMealType = it
                },
                mealText = mealText,
                onValueChange = {
                    mealText = it
                },
                onSend = {
//                    Log.d("SEND", "Button Clicked")
                    if (mealText.isNotBlank()) {
                        isLoading = true
                        viewModel.analyzeMealWithAI(
                            input = mealText,
                            onResult = { food ->
                                Log.d("GEMINI", "Callback reached: $food")
                                val meal = MealLogEntity(
                                    firebaseUid = authRepository.getUid(),
                                    foodName = food.foodName,
                                    mealType = selectedMealType,
                                    quantity = food.quantity,
                                    date = DateUtils.today(),
                                    calories = food.calories,
                                    protein = food.protein,
                                    carbs = food.carbs,
                                    fat = food.fat
                                )
                                viewModel.saveMeal(meal) {
                                    viewModel.getMeals {
                                        meals = it
                                    }
                                    refreshNutritionData()
                                    isLoading = false
                                }
                                mealText = ""
                            },
                            onError = { error ->
                                isLoading = false
                                Toast.makeText(
                                    context,
                                    error,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )
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

//            items(
//                count = messages.size
//            ) { index ->
//                UserMessageBubble(
//                    message = messages[index]
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                if (index < analyses.size) {
//                    AnalysisFoodCard(
//                        title = analyses[index].title,
//                        calories = analyses[index].calories,
//                        protein = analyses[index].protein,
//                        carbs = analyses[index].carbs,
//                        fat = analyses[index].fat,
//                        insight = analyses[index].insight
//                    )
//                }
//            }

            // meal show from room data base
            items(
                count = meals.size
            ) { index ->

                val meal = meals[index]

                val result = MealAnalysisResult(
                    foodName = meal.foodName,
                    quantity = meal.quantity,
                    unit = "Serving",
                    calories = meal.calories,
                    protein = meal.protein,
                    carbs = meal.carbs,
                    fat = meal.fat
                )
                val insight = insightGenerator.generate(result)
                UserMessageBubble(
                    message = meal.foodName,
                    onEditClick = {
                        mealToEdit = meal
                        editedMealText = meal.foodName
                        editedMealType = meal.mealType
                        showEditDialog = true
                    },
                    onDeleteClick = {
                        mealToDelete = meal
                        showDeleteDialog = true
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                AnalysisFoodCard(
                    title = meal.mealType,
                    calories = "${meal.calories} kcal",
                    protein = "${meal.protein} g",
                    carbs = "${meal.carbs} g",
                    fat = "${meal.fat} g",
                    insight = insight
                )
            }

            item {

                SummaryCard(
                    calories = "$totalCaloriesValue kcal",
                    protein = "${totalProteinValue} g",
                    carbs = "${totalCarbsValue} g",
                    fat = "${totalFatValue} g"
                )
            }

            item {

                SuggestionCard(
                    suggestions = suggestions
                )
            }

            item {

                DailySummaryCard(
                    totalCalories = "$totalCaloriesValue kcal",
                    totalProtein = "${totalProteinValue} g",
                    strength = dailySummary.strength,
                    improvement = dailySummary.improvement,
                    tomorrowFocus = dailySummary.tomorrowFocus
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

