package com.example.gharkafit.ui.screen.onBoardingScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier
) {

    var name by rememberSaveable { mutableStateOf("") }
    var age by rememberSaveable { mutableStateOf("") }
    var height by rememberSaveable { mutableStateOf("") }
    var weight by rememberSaveable { mutableStateOf("") }

    var selectedGender by rememberSaveable { mutableStateOf("Male") }
    var selectedGoal by rememberSaveable { mutableStateOf("Fat Loss") }
    var selectedActivity by rememberSaveable { mutableStateOf("Moderate") }
    var selectedDiet by rememberSaveable { mutableStateOf("Mostly Home Food") }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {

            Card(
                shape = RoundedCornerShape(24.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Text(
                        text = "Personal Information",
                        style = MaterialTheme.typography.titleLarge
                    )

                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        label = {
                            Text("Name")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = age,
                        onValueChange = {
                            age = it
                        },
                        label = {
                            Text("Age")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text(
                        text = "Gender",
                        style = MaterialTheme.typography.titleMedium
                    )

                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        SelectionCard(
                            title = "Male",
                            isSelected = selectedGender == "Male",
                            onClick = {
                                selectedGender = "Male"
                            }
                        )

                        SelectionCard(
                            title = "Female",
                            isSelected = selectedGender == "Female",
                            onClick = {
                                selectedGender = "Female"
                            }
                        )
                    }
                }
            }
        }


        item {

            Card(
                shape = RoundedCornerShape(24.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Text(
                        text = "Body Information",
                        style = MaterialTheme.typography.titleLarge
                    )

                    OutlinedTextField(
                        value = height,
                        onValueChange = {
                            height = it
                        },
                        label = {
                            Text("Height (cm)")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = weight,
                        onValueChange = {
                            weight = it
                        },
                        label = {
                            Text("Weight (kg)")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }


        item {

            Card(
                shape = RoundedCornerShape(24.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Text(
                        text = "Fitness Goal",
                        style = MaterialTheme.typography.titleLarge
                    )

                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        SelectionCard(
                            title = "Fat Loss",
                            isSelected = selectedGoal == "Fat Loss",
                            onClick = {
                                selectedGoal = "Fat Loss"
                            }
                        )

                        SelectionCard(
                            title = "Maintain",
                            isSelected = selectedGoal == "Maintain",
                            onClick = {
                                selectedGoal = "Maintain"
                            }
                        )

                        SelectionCard(
                            title = "Muscle Gain",
                            isSelected = selectedGoal == "Muscle Gain",
                            onClick = {
                                selectedGoal = "Muscle Gain"
                            }
                        )
                    }
                }
            }
        }


        item {

            Card(
                shape = RoundedCornerShape(24.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Text(
                        text = "Activity Level",
                        style = MaterialTheme.typography.titleLarge
                    )

                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        SelectionCard(
                            title = "Sedentary",
                            isSelected = selectedActivity == "Sedentary",
                            onClick = {
                                selectedActivity = "Sedentary"
                            }
                        )

                        SelectionCard(
                            title = "Moderate",
                            isSelected = selectedActivity == "Moderate",
                            onClick = {
                                selectedActivity = "Moderate"
                            }
                        )

                        SelectionCard(
                            title = "Active",
                            isSelected = selectedActivity == "Active",
                            onClick = {
                                selectedActivity = "Active"
                            }
                        )
                    }
                }
            }
        }

        /* ---------------- DIET ---------------- */

        item {

            Card(
                shape = RoundedCornerShape(24.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Text(
                        text = "Diet Style",
                        style = MaterialTheme.typography.titleLarge
                    )

                    DietCard(
                        title = "Mostly Home Food",
                        description = "Roti, sabzi, dal, rice, ghar ka khana",
                        isSelected = selectedDiet == "Mostly Home Food",
                        onClick = {
                            selectedDiet = "Mostly Home Food"
                        }
                    )

                    DietCard(
                        title = "Mixed Diet",
                        description = "Some home food, some outside food",
                        isSelected = selectedDiet == "Mixed Diet",
                        onClick = {
                            selectedDiet = "Mixed Diet"
                        }
                    )

                    DietCard(
                        title = "Mostly Processed Food",
                        description = "Fast food, packaged snacks, sugary drinks",
                        isSelected = selectedDiet == "Mostly Processed Food",
                        onClick = {
                            selectedDiet = "Mostly Processed Food"
                        }
                    )
                }
            }
        }

        /* ---------------- BUTTON ---------------- */

        item {

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {

                },
                modifier = Modifier.fillMaxWidth()
            ) {

                Text("Continue")
            }
        }
    }
}

/* ---------------- SELECTION CARD ---------------- */

@Composable
fun SelectionCard(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier.clickable {
            onClick()
        },
        colors = CardDefaults.cardColors(
            containerColor =
                if (isSelected)
                    MaterialTheme.colorScheme.primaryContainer
                else
                    MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = RoundedCornerShape(16.dp)
    ) {

        Text(
            text = title,
            modifier = Modifier.padding(
                horizontal = 20.dp,
                vertical = 14.dp
            )
        )
    }
}

/* ---------------- DIET CARD ---------------- */

@Composable
fun DietCard(
    title: String,
    description: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor =
                if (isSelected)
                    MaterialTheme.colorScheme.primaryContainer
                else
                    MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = RoundedCornerShape(20.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
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