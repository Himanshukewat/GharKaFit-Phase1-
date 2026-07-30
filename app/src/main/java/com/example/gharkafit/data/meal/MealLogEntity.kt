package com.example.gharkafit.data.meal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_logs")
data class MealLogEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "meal_id")
    val mealId: Int = 0,

    @ColumnInfo(name = "firestore_id")
    val firestoreId: String = "",

    @ColumnInfo(name = "firebase_uid")
    val firebaseUid: String,

    @ColumnInfo(name = "user_input")
    val userInput: String,

    @ColumnInfo(name = "food_name")
    val foodName: String,

    @ColumnInfo(name = "meal_type")
    val mealType: String,

    @ColumnInfo(name = "quantity")
    val quantity: Double,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "calories")
    val calories: Int,

    @ColumnInfo(name = "protein")
    val protein: Double,

    @ColumnInfo(name = "carbs")
    val carbs: Double,

    @ColumnInfo(name = "fat")
    val fat: Double
)
