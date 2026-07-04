package com.example.gharkafit.data.meal

class MealRepository(
    private val mealDao: MealDao
) {

    suspend fun insertMeal(meal: MealLogEntity) {
        mealDao.insertMeal(meal)
    }

    suspend fun getTodayMeals(): List<MealLogEntity> {
        return mealDao.getTodayMeals()
    }

    suspend fun deleteMeal(meal: MealLogEntity) {
        mealDao.deleteMeal(meal)
    }

    suspend fun getTotalCalories(): Int {
        return getTodayMeals().sumOf { it.calories }
    }

    suspend fun getTotalProtein(): Double {
        return getTodayMeals().sumOf { it.protein }
    }

    suspend fun getAllMeals(): List<MealLogEntity> {
        return mealDao.getAllMeals()
    }

    suspend fun hasMeal(mealType: String): Boolean {
        return mealDao.getMealCount(mealType) > 0
    }

    suspend fun getTotalCarbs(): Double {
        return mealDao.getTodayMeals().sumOf { it.carbs }
    }

    suspend fun getTotalFat(): Double {
        return mealDao.getTodayMeals().sumOf { it.fat }
    }
}