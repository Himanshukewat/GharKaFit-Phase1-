package com.example.gharkafit.ui.screen.userDetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gharkafit.model.Gender
import com.example.gharkafit.data.MainDatabase
@Composable
fun UserDetail(
    onContinue: () -> Unit
) {

    val context = LocalContext.current

    val db = MainDatabase.getDatabase(context)

    val viewModel: UserDetailVM = viewModel(
        factory = UserDetailVMF(db.userDao())
    )

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
                viewModel.saveUser(
                    age = age.toInt(),
                    height = height.toDouble(),
                    weight = weight.toDouble(),
                    gender = selectedGender.name
                )
                onContinue()
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
