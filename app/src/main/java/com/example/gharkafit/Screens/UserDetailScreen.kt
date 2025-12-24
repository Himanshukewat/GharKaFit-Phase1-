package com.example.gharkafit.Screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gharkafit.core.ActivityLevel
import com.gharkafit.core.Gender
import com.gharkafit.core.Goal
import com.gharkafit.core.UserProfile

@Composable
fun UserDetailScreen(
    onContinue: (UserProfile) -> Unit
) {

    var age by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf(Gender.MALE) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Tell us about yourself",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "This helps us calculate your daily needs accurately.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(5.dp)
        )

        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Height (cm)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(5.dp)
        )

        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(5.dp)
        )

        GenderSelector(
            selectedGender = selectedGender,
            onSelect = { selectedGender = it }
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                onContinue(
                    UserProfile(
                        age = age.toInt(),
                        heightCm = height.toDouble(),
                        weightKg = weight.toDouble(),
                        gender = selectedGender,
                        activityLevel = ActivityLevel.SEDENTARY, // default
                        goal = Goal.MAINTAIN // default
                    )
                )
            },
            enabled = age.isNotBlank() && height.isNotBlank() && weight.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continue")
        }
    }
}

@Composable
fun GenderSelector(
    selectedGender: Gender,
    onSelect: (Gender) -> Unit
) {
    Column {
        Text(
            text = "Gender",
            style = MaterialTheme.typography.titleMedium
        )

        Row {
            Gender.values().forEach { gender ->
                Row(
                    modifier = Modifier.padding(end = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedGender == gender,
                        onClick = { onSelect(gender) }
                    )
                    Text(gender.name)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserDetailsScreenPreview() {
    UserDetailScreen(
        onContinue = {}
    )
}





