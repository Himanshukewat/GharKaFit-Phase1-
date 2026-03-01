package com.example.gharkafit.data.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val userId: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "age")
    val age: Int,

    @ColumnInfo(name = "gender")
    val gender: String,

    @ColumnInfo(name = "height_cm")
    val heightCm: Double,

    @ColumnInfo(name = "weight_kg")
    val weightKg: Double,

    @ColumnInfo(name = "goal")
    val goal: String,

    @ColumnInfo(name = "activity_level")
    val activityLevel: String,

    @ColumnInfo(name = "target_calories")
    val targetCalories: Int,

    @ColumnInfo(name = "target_protein")
    val targetProtein: Double
)
