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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gharkafit.data.MainDatabase
import com.example.gharkafit.data.food.FoodRepository
import com.example.gharkafit.data.meal.MealRepository
import com.example.gharkafit.data.remote.FirestoreRepository
import com.example.gharkafit.data.remote.MealFirestoreRepository
import com.example.gharkafit.data.user.UserEntity
import com.example.gharkafit.data.user.UserRepository
import com.example.gharkafit.ui.component.ProgressInfoCard
import com.example.gharkafit.ui.component.ProgressStat
import com.example.gharkafit.viewmodel.MealViewModel
import com.example.gharkafit.viewmodel.MealViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressScreen(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    val database = remember { MainDatabase.getDatabase(context) }
    val mealRepository = remember { MealRepository(database.mealDao()) }
    val foodRepository = remember { FoodRepository(database.foodDao()) }
    val userRepository = remember { UserRepository(database.userDao()) }
    val firestoreRepository = remember { FirestoreRepository() }
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

    var user by remember { mutableStateOf<UserEntity?>(null) }
    var proteinTargetDays by remember { mutableStateOf(0) }
    var calorieTargetDays by remember { mutableStateOf(0) }
    var trackedDays by remember { mutableStateOf(0) }
    var weeklyInsight by remember { mutableStateOf("") }

    var totalMeals by remember { mutableStateOf(0) }
    val remainingWeight = kotlin.math.abs(
        (user?.weightKg ?: 0.0) -
                (user?.targetWeight ?: 0.0)
    )

    LaunchedEffect(Unit) {
        viewModel.getUser(userRepository) { userData ->
            user = userData
            if (userData != null) {
                viewModel.getWeeklyStats(userData) { stats ->

                    val proteinDays = stats.proteinDays
                    val calorieDays = stats.calorieDays

                    proteinTargetDays = proteinDays
                    calorieTargetDays = calorieDays
                    trackedDays = stats.trackedDays

                    weeklyInsight = when {

                        trackedDays == 0 ->
                            "Start logging your meals today to unlock personalized weekly insights. 🍽️"

                        trackedDays == 1 ->
                            "You've logged meals for 1 day this week. Keep going—consistency starts with small steps! 🌱"

                        trackedDays < 7 ->
                            "You've tracked $trackedDays days this week. Keep logging daily to unlock your complete weekly report. 💪"

                        proteinDays >= 6 && calorieDays >= 6 ->
                            "Excellent work! You stayed consistent with both your calorie and protein goals this week. 🔥"

                        proteinDays >= 5 ->
                            "Great job! Your protein intake has been consistent this week. Keep it up! 💪"

                        calorieDays >= 5 ->
                            "Nice work! You stayed close to your calorie goal on most days this week. 🎯"

                        else ->
                            "You're making progress. Keep logging your meals daily to build healthier habits. 🌟"
                    }
                }
            }
        }

        viewModel.getMealCount {
            totalMeals = it
        }
    }
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
        containerColor = MaterialTheme.colorScheme.background
    )
    { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ProgressInfoCard(
                    title = "Current Goal",
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Text(
                        text = user?.goal
                            ?.replace("_", " ")
                            ?.lowercase()
                            ?.replaceFirstChar { it.uppercase() }
                            ?: "--",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = "Target: ${String.format("%.1f", user?.targetWeight ?: 0.0)} kg",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            item {
                ProgressInfoCard(title = "Weight Progress") {
                    Text(
                        text = "Current Weight: ${String.format("%.1f", user?.weightKg ?: 0.0)} kg",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Text(
                        text = "Goal Weight: ${
                            String.format(
                                "%.1f",
                                user?.targetWeight ?: 0.0
                            )
                        } kg",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Text(
                        text = "Progress: ${String.format("%.1f", remainingWeight)} kg remaining",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            item {
                ProgressInfoCard(title = "This Week") {
                    ProgressStat(
                        label = "Meals Logged",
                        value = "$totalMeals"
                    )
//TODO date-wise tracking when this is dynamic
                    ProgressStat(
                        label = "Protein Target Met",
                        value = "$proteinTargetDays days"
                    )

                    ProgressStat(
                        label = "Calories Target Met",
                        value = "$calorieTargetDays days"
                    )
                }
            }
            item {
                ProgressInfoCard(
                    title = "This Week's Insight 📊",
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Text(
                        text = weeklyInsight,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            item {
                ProgressInfoCard(
                    title = "Keep Going 💪",
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Text(
                        text = "Every healthy choice brings you one step closer to your goal.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}