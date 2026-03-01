package com.example.gharkafit.data.meal

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MealDao {

    @Insert
    suspend fun insertMeal(meal: MealLogEntity)

    @Query("SELECT * FROM meal_logs")
    suspend fun getAllMeals(): List<MealLogEntity>

    @Delete
    suspend fun deleteMeal(meal: MealLogEntity)
}