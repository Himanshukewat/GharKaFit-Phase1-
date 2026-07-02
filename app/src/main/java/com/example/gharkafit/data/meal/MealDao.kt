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
    suspend fun getTodayMeals(): List<MealLogEntity>
    /*
    * Ye actually "today" nahi la raha,ye saare meals la raha hai.
    * */
    @Query("SELECT COUNT(*) FROM meal_logs WHERE meal_type = :mealType")
    suspend fun getMealCount(mealType: String): Int

    @Query("SELECT * FROM meal_logs ORDER BY meal_id DESC")
    suspend fun getAllMeals(): List<MealLogEntity>

    @Delete
    suspend fun deleteMeal(meal: MealLogEntity)
}