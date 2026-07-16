package com.example.gharkafit.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gharkafit.ui.theme.GharKaFitTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.gharkafit.ui.component.ProfileSectionCard
import com.example.gharkafit.ui.component.ProfileSelectionField
import com.example.gharkafit.ui.component.SelectionCard
import com.example.gharkafit.ui.component.TargetInfoCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    name: String,
    age: String,
    gender: String,
    height: String,
    weight: String,
    goal: String,
    diet: String,
    activity: String,
    calories: String,
    protein: String,
    targetWeight: String,
    water: String,
    onNameChange: (String) -> Unit,
    onAgeChange: (String) -> Unit,
    onHeightChange: (String) -> Unit,
    onWeightChange: (String) -> Unit,
    onSave: () -> Unit
) {

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
                        onValueChange = onNameChange,
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Full Name")
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = age,
                        onValueChange = onAgeChange,
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Age")
                        }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    ProfileSelectionField(
                        label = "Gender",
                        selectedValue = gender,
                        options = listOf(
                            "Male",
                            "Female"
                        ),
                        onValueSelected = {

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
                        onValueChange = onHeightChange,
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Height (cm)")
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = weight,
                        onValueChange = onWeightChange,
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Weight (kg)")
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    ProfileSelectionField(
                        label = "Goal",
                        selectedValue = goal,
                        options = listOf(
                            "Fat Loss",
                            "Muscle Gain",
                            "Maintain Weight"
                        ),
                        onValueSelected = {

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
                        options = listOf(
                            "Mixed Food",
                            "Mostly Processed",
                            "Home Made"
                        ),
                        onValueSelected = {

                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    ProfileSelectionField(
                        label = "Activity Level",
                        selectedValue = activity,
                        options = listOf(
                            "Sedentary",
                            "Active",
                            "Very Active"
                        ),
                        onValueSelected = {

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

                Button(
                    onClick = onSave,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save Changes")
                }
            }
        }
    }
}
