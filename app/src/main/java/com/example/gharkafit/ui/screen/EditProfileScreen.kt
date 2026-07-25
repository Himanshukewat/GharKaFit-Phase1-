package com.example.gharkafit.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gharkafit.core.Calculator
import com.example.gharkafit.core.TargetWeightCalculator
import com.example.gharkafit.data.MainDatabase
import com.example.gharkafit.data.food.FoodRepository
import com.example.gharkafit.data.meal.MealRepository
import com.example.gharkafit.data.remote.FirestoreRepository
import com.example.gharkafit.data.remote.MealFirestoreRepository
import com.example.gharkafit.data.user.UserEntity
import com.example.gharkafit.data.user.UserRepository
import com.example.gharkafit.model.ActivityLevel
import com.example.gharkafit.model.DietHabit
import com.example.gharkafit.model.Gender
import com.example.gharkafit.model.Goal
import com.example.gharkafit.ui.component.ProfileSectionCard
import com.example.gharkafit.ui.component.ProfileSelectionField
import com.example.gharkafit.ui.component.TargetInfoCard
import com.example.gharkafit.viewmodel.MealViewModel
import com.example.gharkafit.viewmodel.MealViewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    onSave: () -> Unit
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
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    var gender by remember { mutableStateOf("") }
    var goal by remember { mutableStateOf("") }
    var diet by remember { mutableStateOf("") }
    var activity by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }
    var protein by remember { mutableStateOf("") }
    var targetWeight by remember { mutableStateOf("") }
    var water by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.getUser(userRepository) { data ->
            user = data
            if (data != null) {
                name = data.name
                age = data.age.toString()
                height = data.heightCm.toString()
                weight = data.weightKg.toString()
                gender = data.gender
                goal = data.goal
                diet = data.dietHabit
                activity = data.activityLevel
            }
        }
    }
    LaunchedEffect(
        weight,
        height,
        age,
        gender,
        goal,
        activity
    ) {

        if (
            weight.isBlank() ||
            height.isBlank() ||
            age.isBlank()
        ) return@LaunchedEffect

        val weightValue = weight.toDoubleOrNull() ?: return@LaunchedEffect
        val heightValue = height.toDoubleOrNull() ?: return@LaunchedEffect
        val ageValue = age.toIntOrNull() ?: return@LaunchedEffect

        val caloriesValue =
            Calculator.calculateDailyCalories(
                weightKg = weightValue,
                heightCm = heightValue,
                age = ageValue,
                gender = gender,
                activityLevel = activity,
                goal = goal
            )

        val proteinValue =
            Calculator.calculateDailyProtein(
                weightKg = weightValue,
                goal = goal
            )

        val targetWeightValue =
            TargetWeightCalculator.calculate(
                heightCm = heightValue,
                currentWeight = weightValue,
                goal = goal
            )

        val waterValue = when (activity) {
            "SEDENTARY" -> 2.5
            "LIGHT" -> 3.0
            "ACTIVE" -> 4.0
            else -> 3.0
        }

        calories = "$caloriesValue kcal"
        protein = "${proteinValue.toInt()} g"
        targetWeight = "${String.format("%.1f", targetWeightValue)} kg"
        water = "${String.format("%.1f", waterValue)} L"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Edit Profile")
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
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        modifier = Modifier.size(90.dp),
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.primaryContainer
                    ) {
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Change Avatar (Future)",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            item {

                ProfileSectionCard(
                    title = "📝 Personal Information"
                ) {

                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        label = {
                            Text("Full Name")
                        }
                    )


                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = age,
                        onValueChange = {
                            age = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Age")
                        }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    ProfileSelectionField(
                        label = "Gender",
                        selectedValue = gender,
                        options = Gender.entries.map {
                            it.name
                        },
                        onValueSelected = {
                            gender = it
                        }
                    )
                }
            }
            item {
                ProfileSectionCard(
                    title = "📏 Body Details"
                ) {

                    OutlinedTextField(
                        value = height,
                        onValueChange = { height = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Height (cm)")
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = weight,
                        onValueChange = { weight = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Weight (kg)")
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    ProfileSelectionField(
                        label = "Goal",
                        selectedValue = goal,
                        options = Goal.entries.map {
                            it.name
                        },
                        onValueSelected = {
                            goal = it
                        }
                    )
                }
            }

            item {

                ProfileSectionCard(
                    title = "🥗 Lifestyle"
                ) {
                    ProfileSelectionField(
                        label = "Diet Habit",
                        selectedValue = diet,
                        options = DietHabit.entries.map {
                            it.name
                        },
                        onValueSelected = {
                            diet = it
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    ProfileSelectionField(
                        label = "Activity Level",
                        selectedValue = activity,
                        options = ActivityLevel.entries.map {
                            it.name
                        },
                        onValueSelected = {
                            activity = it
                        }
                    )
                }
            }

            item {
                TargetInfoCard(
                    calories = calories,
                    protein = protein,
                    targetWeight = targetWeight,
                    water = water
                )
            }

            item {

                val updatedUser = user?.copy(
                    name = name,
                    age = age.toInt(),
                    gender = gender,
                    heightCm = height.toDouble(),
                    weightKg = weight.toDouble(),
                    goal = goal,
                    dietHabit = diet,
                    activityLevel = activity,
                    targetCalories = calories.substringBefore(" ").toInt(),
                    targetProtein = protein.substringBefore(" ").toDouble(),
                    targetWeight = targetWeight.substringBefore(" ").toDouble(),
                    waterTarget = water.substringBefore(" ").toDouble()

                )

                Button(
                    onClick = {
                        updatedUser?.let {
                            viewModel.updateUser(
                                repository = userRepository,
                                user = it
                            ) {
                                onSave()
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save Changes")
                }
            }
        }
    }
}
