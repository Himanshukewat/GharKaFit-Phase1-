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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gharkafit.ui.component.DietCard
import com.example.gharkafit.ui.component.GoalCard
import com.example.gharkafit.ui.component.SelectionCard

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier
) {

    var name by rememberSaveable { mutableStateOf("") }
    var age by rememberSaveable { mutableStateOf("") }
    var height by rememberSaveable { mutableStateOf("") }
    var weight by rememberSaveable { mutableStateOf("") }

    var selectedGender by rememberSaveable {
        mutableStateOf("Male")
    }

    var selectedGoal by rememberSaveable {
        mutableStateOf("Fat Loss")
    }

    var selectedActivity by rememberSaveable {
        mutableStateOf("Moderate")
    }

    var selectedDiet by rememberSaveable {
        mutableStateOf("Mostly Home Food")
    }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        // HEADER

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


        // PERSONAL INFO

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
                            title = "Male", isSelected = selectedGender == "Male", onClick = {
                                selectedGender = "Male"
                            }, modifier = Modifier.weight(1f)
                        )

                        SelectionCard(
                            title = "Female", isSelected = selectedGender == "Female", onClick = {
                                selectedGender = "Female"
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
                        isSelected = selectedGoal == "Fat Loss",
                        onClick = {
                            selectedGoal = "Fat Loss"
                        })

                    GoalCard(
                        title = "Maintain",
                        description = "Stay healthy and active",
                        isSelected = selectedGoal == "Maintain",
                        onClick = {
                            selectedGoal = "Maintain"
                        })

                    GoalCard(
                        title = "Muscle Gain",
                        description = "Increase strength and muscle mass",
                        isSelected = selectedGoal == "Muscle Gain",
                        onClick = {
                            selectedGoal = "Muscle Gain"
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
                        isSelected = selectedActivity == "Mostly Sitting",
                        onClick = {
                            selectedActivity = "Mostly Sitting"
                        })

                    GoalCard(
                        title = "Lightly Active",
                        description = "Walks and light daily activity",
                        isSelected = selectedActivity == "Lightly Active",
                        onClick = {
                            selectedActivity = "Lightly Active"
                        })

                    GoalCard(
                        title = "Very Active",
                        description = "Workout and high daily movement",
                        isSelected = selectedActivity == "Very Active",
                        onClick = {
                            selectedActivity = "Very Active"
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
                        isSelected = selectedDiet == "Mostly Home Food",
                        onClick = {
                            selectedDiet = "Mostly Home Food"
                        })

                    DietCard(
                        title = "Mixed Diet",
                        description = "Some home food and some outside food",
                        isSelected = selectedDiet == "Mixed Diet",
                        onClick = {
                            selectedDiet = "Mixed Diet"
                        })

                    DietCard (
                        title = "Mostly Processed Food",
                        description = "Fast food and packaged snacks",
                        isSelected = selectedDiet == "Mostly Processed Food",
                        onClick = {
                            selectedDiet = "Mostly Processed Food"
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
                    containerColor = Color(0xFF4CAF50)
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

    MaterialTheme {
        OnboardingScreen()
    }
}