package com.example.gharkafit.data.food

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FoodDao {

    @Insert
    suspend fun insertFood(food: FoodEntity)

    @Insert
    suspend fun insertAllFoods(foods: List<FoodEntity>)

    @Query("SELECT * FROM foods")
    suspend fun getAllFoods(): List<FoodEntity>
}