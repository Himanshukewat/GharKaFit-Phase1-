package com.example.gharkafit.data.remote

import android.util.Log
import com.example.gharkafit.data.user.UserEntity
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreRepository {
    private val firestore = FirebaseFirestore.getInstance()
// firebaseUid hi document ID banega
    fun saveUser(
        user: UserEntity,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
    Log.d("FIRESTORE", "Inside saveUser()")

    firestore
        .collection("users")
        .document(user.firebaseUid)
        .set(user)
        .addOnSuccessListener {
            Log.d("FIRESTORE", "Firestore Success")
            onSuccess()
        }
        .addOnFailureListener {
            Log.e("FIRESTORE", "Firestore Error", it)
            onError(it.message ?: "Failed")
        }
    }

    fun testFirestore(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        firestore
            .collection("test")
            .document("demo")
            .set(mapOf("name" to "Himanshu"))
            .addOnSuccessListener {
                Log.d("FIRESTORE", "Test Success")
                onSuccess()
            }
            .addOnFailureListener {
                Log.e("FIRESTORE", "Test Error", it)
                onError(it.message ?: "Failed")
            }
    }
}