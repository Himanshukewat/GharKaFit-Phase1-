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

    @Delete
    suspend fun deleteMeal(meal: MealLogEntity)
}