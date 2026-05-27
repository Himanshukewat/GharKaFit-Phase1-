package com.example.gharkafit.ui.screen.onBoardingScreen

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier
) {

    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var height by remember{ mutableStateOf("") }
    var weight by rememberSaveable { mutableStateOf("") }

    var selectedGender by remember {
        mutableStateOf(Gender.MALE)
    }

    var selectedGoal by remember {
        mutableStateOf(Goal.FAT_LOSS)
    }

    var selectedActivity by remember {
        mutableStateOf(ActivityLevel.MODERATE)
    }

    var selectedDiet by remember {
        mutableStateOf(DietHabit.HOME)
    }

    LazyColumn(
        modifier = modifier,
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
                        value = name, onValueChange = {
                        name = it
                    }, label = {
                        Text("Name")
                    }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp)
                    )

                    OutlinedTextField(
                        value = age, onValueChange = {
                        age = it
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
                            title = "Male", isSelected = selectedGender == Gender.MALE, onClick = {
                                selectedGender = Gender.MALE
                            }, modifier = Modifier.weight(1f)
                        )

                        SelectionCard(
                            title = "Female", isSelected = selectedGender == Gender.FEMALE, onClick = {
                                selectedGender = Gender.FEMALE
                            }, modifier = Modifier.weight(1f)
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

                        OutlinedTextField(value = height, onValueChange = {
                            height = it
                        }, label = {
                            Text("Height")
                        }, suffix = {
                            Text("cm")
                        }, keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ), modifier = Modifier.weight(1f), shape = RoundedCornerShape(18.dp))

                        OutlinedTextField(
                            value = weight, onValueChange = {
                            weight = it
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
                        isSelected = selectedGoal == Goal.FAT_LOSS,
                        onClick = {
                            selectedGoal = Goal.FAT_LOSS
                        })

                    GoalCard(
                        title = "Maintain",
                        description = "Stay healthy and active",
                        isSelected = selectedGoal == Goal.MAINTAIN,
                        onClick = {
                            selectedGoal = Goal.MAINTAIN
                        })

                    GoalCard(
                        title = "Muscle Gain",
                        description = "Increase strength and muscle mass",
                        isSelected = selectedGoal == Goal.MUSCLE_GAIN,
                        onClick = {
                            selectedGoal = Goal.MUSCLE_GAIN
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
                        isSelected = selectedActivity == ActivityLevel.SEDENTARY,
                        onClick = {
                            selectedActivity = ActivityLevel.SEDENTARY
                        })

                    GoalCard(
                        title = "Lightly Active",
                        description = "Walks and light daily activity",
                        isSelected = selectedActivity == ActivityLevel.MODERATE,
                        onClick = {
                            selectedActivity = ActivityLevel.MODERATE
                        })

                    GoalCard(
                        title = "Very Active",
                        description = "Workout and high daily movement",
                        isSelected = selectedActivity == ActivityLevel.ACTIVE,
                        onClick = {
                            selectedActivity = ActivityLevel.ACTIVE
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

                    DietCard (
                        title = "Mostly Home Food",
                        description = "Roti, sabzi, dal, rice",
                        isSelected = selectedDiet == DietHabit.HOME,
                        onClick = {
                            selectedDiet = DietHabit.HOME
                        })

                    DietCard(
                        title = "Mixed Diet",
                        description = "Some home food and some outside food",
                        isSelected = selectedDiet == DietHabit.MIXED,
                        onClick = {
                            selectedDiet = DietHabit.MIXED
                        })

                    DietCard (
                        title = "Mostly Processed Food",
                        description = "Fast food and packaged snacks",
                        isSelected = selectedDiet == DietHabit.PROCESSED,
                        onClick = {
                            selectedDiet = DietHabit.PROCESSED
                        })
                }
            }
        }


        item {

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor =  MaterialTheme.colorScheme.primary
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
        OnboardingScreen()
    }
}