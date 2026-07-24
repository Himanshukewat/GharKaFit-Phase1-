package com.example.gharkafit.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gharkafit.model.ActivityLevel
import com.example.gharkafit.model.DietHabit
import com.example.gharkafit.ui.component.DietCard
import com.example.gharkafit.ui.component.GoalCard
import com.example.gharkafit.model.Gender
import com.example.gharkafit.model.Goal
import com.example.gharkafit.ui.component.SelectionCard
import com.example.gharkafit.ui.theme.GharKaFitTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gharkafit.data.user.UserEntity
import com.example.gharkafit.viewmodel.OnboardingViewModel
import android.widget.Toast
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.gharkafit.auth.AuthRepository
import com.example.gharkafit.data.MainDatabase
import com.example.gharkafit.data.user.UserRepository
import com.example.gharkafit.viewmodel.OnboardingViewModelFactory

@Composable
fun OnboardingScreen(
    onContinueClick: (UserEntity) -> Unit
) {
    val context = LocalContext.current
    val database = remember { MainDatabase.getDatabase(context) }
    val repository = remember { UserRepository(database.userDao()) }
    val authRepository = remember { AuthRepository() }
    val factory = remember { OnboardingViewModelFactory(repository,authRepository) }
    val viewModel: OnboardingViewModel = viewModel(factory = factory)
    val uiState = viewModel.uiState

    LazyColumn(
        modifier = Modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        item {

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = "Tell us about yourself", style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = "We’ll personalize your nutrition journey",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }



        item {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Text(
                        text = "Personal Information", style = MaterialTheme.typography.titleLarge
                    )

                    OutlinedTextField(
                        value = uiState.name, onValueChange = {
                            viewModel.updateName(it)
                        }, label = {
                            Text("Name")
                        }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp)
                    )

                    OutlinedTextField(
                        value = uiState.age, onValueChange = {
                            viewModel.updateAge(it)
                        }, label = {
                            Text("Age")
                        }, keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ), modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp)
                    )

                    Text(
                        text = "Gender", style = MaterialTheme.typography.titleMedium
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        SelectionCard(
                            title = "Male", isSelected = uiState.gender == Gender.MALE, onClick = {
                                viewModel.updateGender(Gender.MALE)
                            }, modifier = Modifier.weight(1f)
                        )

                        SelectionCard(
                            title = "Female",
                            isSelected = uiState.gender == Gender.FEMALE,
                            onClick = {
                                viewModel.updateGender(Gender.FEMALE)
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        item {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Text(
                        text = "Body Information", style = MaterialTheme.typography.titleLarge
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        OutlinedTextField(
                            value = uiState.height, onValueChange = {
                            viewModel.updateHeight(it)
                        }, label = {
                            Text("Height")
                        }, suffix = {
                            Text("cm")
                        }, keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ), modifier = Modifier.weight(1f), shape = RoundedCornerShape(18.dp)
                        )

                        OutlinedTextField(
                            value = uiState.weight, onValueChange = {
                                viewModel.updateWeight(it)
                            }, label = {
                                Text("Weight")
                            }, suffix = {
                                Text("kg")
                            }, keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ), modifier = Modifier.weight(1f), shape = RoundedCornerShape(18.dp)
                        )
                    }
                }
            }
        }

        item {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Text(
                        text = "Fitness Goal", style = MaterialTheme.typography.titleLarge
                    )

                    GoalCard(
                        title = "Fat Loss",
                        description = "Lose weight while maintaining muscle",
                        isSelected = uiState.goal == Goal.WEIGHT_LOSS,
                        onClick = {
                            viewModel.updateGoal(Goal.WEIGHT_LOSS)
                        })

                    GoalCard(
                        title = "Maintain",
                        description = "Stay healthy and active",
                        isSelected = uiState.goal == Goal.MAINTAIN,
                        onClick = {
                            viewModel.updateGoal(Goal.MAINTAIN)
                        })

                    GoalCard(
                        title = "Muscle Gain",
                        description = "Increase strength and muscle mass",
                        isSelected = uiState.goal == Goal.MUSCLE_GAIN,
                        onClick = {
                            viewModel.updateGoal(Goal.MUSCLE_GAIN)
                        })
                }
            }
        }

        item {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Text(
                        text = "Activity Level", style = MaterialTheme.typography.titleLarge
                    )

                    GoalCard(
                        title = "Mostly Sitting",
                        description = "Desk work and low movement",
                        isSelected = uiState.activityLevel == ActivityLevel.SEDENTARY,
                        onClick = {
                            viewModel.updateActivity(ActivityLevel.SEDENTARY)
                        })

                    GoalCard(
                        title = "Lightly Active",
                        description = "Walks and light daily activity",
                        isSelected = uiState.activityLevel == ActivityLevel.MODERATE,
                        onClick = {
                            viewModel.updateActivity(ActivityLevel.MODERATE)
                        })

                    GoalCard(
                        title = "Very Active",
                        description = "Workout and high daily movement",
                        isSelected = uiState.activityLevel == ActivityLevel.ACTIVE,
                        onClick = {
                            viewModel.updateActivity(ActivityLevel.ACTIVE)
                        })
                }
            }
        }


        item {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Text(
                        text = "Diet Style", style = MaterialTheme.typography.titleLarge
                    )

                    DietCard(
                        title = "Mostly Home Food",
                        description = "Roti, sabzi, dal, rice",
                        isSelected = uiState.dietHabit == DietHabit.HOME,
                        onClick = {
                            viewModel.updateDiet(DietHabit.HOME)
                        })

                    DietCard(
                        title = "Mixed Diet",
                        description = "Some home food and some outside food",
                        isSelected = uiState.dietHabit == DietHabit.MIXED,
                        onClick = {
                            viewModel.updateDiet(DietHabit.MIXED)
                        })

                    DietCard(
                        title = "Mostly Processed Food",
                        description = "Fast food and packaged snacks",
                        isSelected = uiState.dietHabit == DietHabit.PROCESSED,
                        onClick = {
                            viewModel.updateDiet(DietHabit.PROCESSED)
                        })
                }
            }
        }


        item {

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val error = viewModel.validateInput()
                    if (error == null) {
                        val user = viewModel.createUserEntity()
                        viewModel.saveUser(user)
                        Log.d("USER_DATA", user.toString())
                        onContinueClick(user)
                    } else {
                        Toast.makeText(
                            context,
                            error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
//                onClick = {
//                    val user = viewModel.createUserEntity()
//                    onContinueClick(user)
//                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(18.dp)
            ) {

                Text(
                    text = "Continue"
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {

    GharKaFitTheme {
        OnboardingScreen(
            onContinueClick = {}
        )
    }
}