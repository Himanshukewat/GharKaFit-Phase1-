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

//    fun testFirestore(
//        onSuccess: () -> Unit,
//        onError: (String) -> Unit
//    ) {
//        firestore
//            .collection("test")
//            .document("demo")
//            .set(mapOf("name" to "Himanshu"))
//            .addOnSuccessListener {
//                Log.d("FIRESTORE", "Test Success")
//                onSuccess()
//            }
//            .addOnFailureListener {
//                Log.e("FIRESTORE", "Test Error", it)
//                onError(it.message ?: "Failed")
//            }
//    }

//    fun getUser(
//        uid: String,
//        onSuccess: (UserEntity?) -> Unit,
//        onError: (String) -> Unit
//    ) {
//        Log.d("FIRESTORE", "Fetching UID = $uid")
//        firestore
//            .collection("users")
//            .document(uid)
//            .get()
//            .addOnSuccessListener { document ->
//                Log.d("FIRESTORE", "Document Exists = ${document.exists()}")
//                Log.d("FIRESTORE", "Document Data = ${document.data}")
//                val user = document.toObject(UserEntity::class.java)
//                Log.d("FIRESTORE", "User Object = $user")
//                onSuccess(user)
//            }
//            .addOnFailureListener {
//                Log.e("FIRESTORE", "Fetch Error", it)
//                onError(it.message ?: "Failed")
//            }
//    }

    fun getUser(
        uid: String,
        onSuccess: (UserEntity?) -> Unit,
        onError: (String) -> Unit
    ) {
        firestore
            .collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener { document ->

                if (!document.exists()) {
                    onSuccess(null)
                    return@addOnSuccessListener
                }
                val user = UserEntity(
                    userId = (document.getLong("userId") ?: 0L).toInt(),
                    firebaseUid = document.getString("firebaseUid") ?: "",
                    name = document.getString("name") ?: "",
                    age = (document.getLong("age") ?: 0L).toInt(),
                    gender = document.getString("gender") ?: "",
                    heightCm = document.getDouble("heightCm") ?: 0.0,
                    weightKg = document.getDouble("weightKg") ?: 0.0,
                    goal = document.getString("goal") ?: "",
                    startWeight = document.getDouble("startWeight") ?: 0.0,
                    targetWeight = document.getDouble("targetWeight") ?: 0.0,
                    waterTarget = document.getDouble("waterTarget") ?: 0.0,
                    dietHabit = document.getString("dietHabit") ?: "",
                    activityLevel = document.getString("activityLevel") ?: "",
                    targetCalories = (document.getLong("targetCalories") ?: 0L).toInt(),
                    targetProtein = document.getDouble("targetProtein") ?: 0.0
                )
                onSuccess(user)
            }
            .addOnFailureListener {
                onError(it.message ?: "Failed to fetch user")
            }
    }
}