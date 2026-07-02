package com.example.gharkafit.data.food

class FoodRepository(
    private val foodDao: FoodDao
) {

    suspend fun insertFood(food: FoodEntity) {
        foodDao.insertFood(food)
    }

    suspend fun insertAllFoods(foods: List<FoodEntity>) {
        foodDao.insertAllFoods(foods)
    }

    suspend fun getAllFoods(): List<FoodEntity> {
        return foodDao.getAllFoods()
    }

    suspend fun getFoodByName(foodName: String): FoodEntity? {
        return foodDao.getFoodByName(foodName)
    }

    suspend fun seedFoods(foods: List<FoodEntity>) {
        if (foodDao.getAllFoods().isEmpty()) {
            foodDao.insertAllFoods(foods)
        }
    }
}