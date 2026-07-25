package com.example.gharkafit.data.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val userId: Int = 0,

    @ColumnInfo(name = "firebase_uid")
    val firebaseUid: String = "",

    @ColumnInfo(name = "name")
    val name: String = "",

    @ColumnInfo(name = "age")
    val age: Int = 0,

    @ColumnInfo(name = "gender")
    val gender: String = "",

    @ColumnInfo(name = "height_cm")
    val heightCm: Double = 0.0,

    @ColumnInfo(name = "weight_kg")
    val weightKg: Double = 0.0,

    @ColumnInfo(name = "goal")
    val goal: String = "",

    @ColumnInfo(name = "start_weight")
    val startWeight: Double = 0.0,

    @ColumnInfo(name = "target_weight")
    val targetWeight: Double = 0.0,

    @ColumnInfo(name = "water_target")
    val waterTarget: Double = 0.0,

    @ColumnInfo(name = "diet_habit")
    val dietHabit: String = "",

    @ColumnInfo(name = "activity_level")
    val activityLevel: String = "",

    @ColumnInfo(name = "target_calories")
    val targetCalories: Int = 0,

    @ColumnInfo(name = "target_protein")
    val targetProtein: Double = 0.0
)