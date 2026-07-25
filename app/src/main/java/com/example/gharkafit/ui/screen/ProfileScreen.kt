package com.example.gharkafit.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gharkafit.data.MainDatabase
import com.example.gharkafit.data.food.FoodRepository
import com.example.gharkafit.data.meal.MealRepository
import com.example.gharkafit.data.remote.FirestoreRepository
import com.example.gharkafit.data.remote.MealFirestoreRepository
import com.example.gharkafit.data.user.UserEntity
import com.example.gharkafit.data.user.UserRepository
import com.example.gharkafit.ui.component.ProfileHeader
import com.example.gharkafit.ui.component.ProfileInfo
import com.example.gharkafit.ui.component.ProfileSectionCard
import com.example.gharkafit.ui.theme.GharKaFitTheme
import com.example.gharkafit.viewmodel.MealViewModel
import com.example.gharkafit.viewmodel.MealViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onEditProfile: () -> Unit,
    onLogout: () -> Unit
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

    LaunchedEffect(Unit) {
        viewModel.getUser(userRepository) {
            user = it
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("My Profile")
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ProfileHeader(
                    name = user?.name ?: "--",
                    quote = "Stay Consistent and Just Do It"
                )
            }
            item {
                ProfileSectionCard(
                    title = "📊 Health Overview"
                ) {
                    ProfileInfo("⚖", "Current Weight", "${user?.weightKg ?: 0.0} kg")
                    ProfileInfo("🎯", "Goal", user?.goal
                        ?.replace("_", " ")
                        ?.lowercase()
                        ?.replaceFirstChar { it.uppercase() }
                        ?: "--")
                    ProfileInfo("📏", "Height", "${user?.heightCm ?: 0.0} cm")
                    ProfileInfo("🎂", "Age", "${user?.age ?: 0}")
                    ProfileInfo("🚹", "Gender", user?.gender ?: "--")
                }
            }
            item {
                ProfileSectionCard(
                    title = "Lifestyle"
                ) {
                    ProfileInfo("🍽", "Diet Habit", user?.dietHabit
                        ?.replace("_", " ")
                        ?.lowercase()
                        ?.replaceFirstChar { it.uppercase() }
                        ?: "--")
                    ProfileInfo("🏃", "Activity Level", user?.activityLevel
                        ?.replace("_", " ")
                        ?.lowercase()
                        ?.replaceFirstChar { it.uppercase() }
                        ?: "--")
                }
            }

            item {
                ProfileSectionCard(
                    title = "🎯 Daily Targets"
                ) {
                    ProfileInfo("🔥", "Calories", "${user?.targetCalories ?: 0} kcal")
                    ProfileInfo("💪", "Protein", "${user?.targetProtein?.toInt() ?: 0} g")
                    ProfileInfo("💧", "Water", "${String.format("%.1f", user?.waterTarget ?: 0.0)} L")
                }
            }
            item {
                Button(
                    onClick = onEditProfile,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Edit Profile")
                }
            }
            item {
                Button(
                    onClick = onLogout,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Logout")
                }
            }
        }
    }
}