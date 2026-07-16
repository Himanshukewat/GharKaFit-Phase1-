package com.example.gharkafit.data.meal

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MealDao {

    @Insert
    suspend fun insertMeal(meal: MealLogEntity)

    @Query("SELECT * FROM meal_logs WHERE date = :date")
    suspend fun getTodayMeals(date: String): List<MealLogEntity>

    /*
    * Ye actually "today" nahi la raha,ye saare meals la raha hai.
    * */
    @Query("SELECT COUNT(*) FROM meal_logs WHERE meal_type = :mealType")
    suspend fun getMealCount(mealType: String): Int

    @Query("SELECT * FROM meal_logs ORDER BY meal_id DESC")
    suspend fun getAllMeals(): List<MealLogEntity>

    @Query("""SELECT DISTINCT date FROM meal_logs ORDER BY date DESC LIMIT 7 """)
    suspend fun getLast7Dates(): List<String>

    @Query(""" SELECT SUM(calories) FROM meal_logs WHERE date = :date """)
    suspend fun getCaloriesByDate( date: String ): Int?

    @Query("""SELECT SUM(protein) FROM meal_logs WHERE date = :date """)
    suspend fun getProteinByDate( date: String ): Double?

    @Delete
    suspend fun deleteMeal(meal: MealLogEntity)
}