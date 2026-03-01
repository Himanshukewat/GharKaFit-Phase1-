package com.example.gharkafit.data.food

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foods")
data class FoodEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "food_id")
    val foodId: Int = 0,

    @ColumnInfo(name = "food_name")
    val foodName: String,

    @ColumnInfo(name = "serving_size")
    val servingSize: Double,

    @ColumnInfo(name = "serving_unit")
    val servingUnit: String,

    @ColumnInfo(name = "calories")
    val calories: Int,

    @ColumnInfo(name = "protein")
    val protein: Double,

    @ColumnInfo(name = "carbs")
    val carbs: Double,

    @ColumnInfo(name = "fat")
    val fat: Double
)
