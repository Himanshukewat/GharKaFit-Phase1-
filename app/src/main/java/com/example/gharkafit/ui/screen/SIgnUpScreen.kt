package com.example.gharkafit.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gharkafit.ui.component.AuthHeader
import com.example.gharkafit.ui.component.AuthTextField
import com.example.gharkafit.ui.component.ErrorText
import com.example.gharkafit.ui.component.LoadingButton
import com.example.gharkafit.ui.component.PasswordTextField
import com.example.gharkafit.ui.theme.GharKaFitTheme

@Composable
fun SignupScreen(
    email: String,
    password: String,
    confirmPassword: String,
    isLoading: Boolean,
    error: String?,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onSignupClick: () -> Unit,
    onLoginClick: () -> Unit
) {

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            AuthHeader(
                title = "Create Account",
                subtitle = "Start your healthy journey today."
            )
            Spacer(modifier = Modifier.height(32.dp))
            AuthTextField(
                value = email,
                onValueChange = onEmailChange,
                label = "Email",
                leadingIcon = Icons.Outlined.Email
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordTextField(
                value = password,
                onValueChange = onPasswordChange,
                label = "Password"
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordTextField(
                value = confirmPassword,
                onValueChange = onConfirmPasswordChange,
                label = "Confirm Password"
            )
            if (!error.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(16.dp))
                ErrorText(
                    message = error
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            LoadingButton(
                text = "Create Account",
                loadingText = "Creating Account...",
                isLoading = isLoading,
                onClick = onSignupClick
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already have an account?"
                )
                TextButton(
                    onClick = onLoginClick
                ) {
                    Text("Login")
                }
            }
        }
    }
}