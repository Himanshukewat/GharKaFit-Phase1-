package com.example.gharkafit.auth

import com.google.firebase.auth.FirebaseAuth

class AuthRepository {
    private val auth = FirebaseAuth.getInstance()
    fun getCurrentUser() = auth.currentUser
// this method will be send request firebase
    fun signUp(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onError(it.message ?: "Signup Failed")
            }
    }
    fun logout() {
        auth.signOut()
    }
}