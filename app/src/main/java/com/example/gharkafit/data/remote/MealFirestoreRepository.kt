package com.example.gharkafit.data.remote

import com.example.gharkafit.data.meal.MealLogEntity
import com.google.firebase.firestore.FirebaseFirestore

class MealFirestoreRepository {
    private val firestore = FirebaseFirestore.getInstance()
    fun saveMeal(
        meal: MealLogEntity,
        onSuccess: (MealLogEntity) -> Unit,
        onError: (String) -> Unit
    ) {
        val docRef = firestore
            .collection("users")
            .document(meal.firebaseUid)
            .collection("meals")
            .document()

        val updatedMeal = meal.copy(
            firestoreId = docRef.id
        )

        docRef
            .set(updatedMeal)
            .addOnSuccessListener {
                onSuccess(updatedMeal)
            }
            .addOnFailureListener {
                onError(it.message ?: "Failed")
            }
    }

    // fetch meal from firestore
    fun getMeals(
        uid: String,
        onSuccess: (List<MealLogEntity>) -> Unit,
        onError: (String) -> Unit
    ) {
        firestore
            .collection("users")
            .document(uid)
            .collection("meals")
            .get()
            .addOnSuccessListener { documents ->
                android.util.Log.d("MEAL_FETCH", "Documents = ${documents.size()}")
                val meals = mutableListOf<MealLogEntity>()
                for (document in documents) {
                    android.util.Log.d("MEAL_FETCH", document.data.toString())

                    val meal = MealLogEntity(
                        mealId = 0,
                        firestoreId = document.id,
                        firebaseUid = document.getString("firebaseUid") ?: "",
                        foodName = document.getString("foodName") ?: "",
                        mealType = document.getString("mealType") ?: "",
                        quantity = document.getDouble("quantity") ?: 0.0,
                        date = document.getString("date") ?: "",
                        calories = (document.getLong("calories") ?: 0L).toInt(),
                        protein = document.getDouble("protein") ?: 0.0,
                        carbs = document.getDouble("carbs") ?: 0.0,
                        fat = document.getDouble("fat") ?: 0.0
                    )

                    meals.add(meal)
                }
                android.util.Log.d("MEAL_FETCH", "Meals List = $meals")

                onSuccess(meals)
            }
            .addOnFailureListener {
                onError(it.message ?: "Failed to fetch meals")
            }
    }


    fun deleteMeal(
        meal: MealLogEntity,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        android.util.Log.d("DELETE_MEAL", "UID = ${meal.firebaseUid}")
        android.util.Log.d("DELETE_MEAL", "FirestoreId = ${meal.firestoreId}")

        firestore
            .collection("users")
            .document(meal.firebaseUid)
            .collection("meals")
            .document(meal.firestoreId)
            .delete()
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onError(it.message ?: "Failed")
            }
    }
}