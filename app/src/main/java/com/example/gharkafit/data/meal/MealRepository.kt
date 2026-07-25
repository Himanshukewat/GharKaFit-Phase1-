package com.example.gharkafit.data.meal

class MealRepository(
    private val mealDao: MealDao
) {

    suspend fun insertMeal(meal: MealLogEntity) {
        mealDao.insertMeal(meal)
    }

    suspend fun getTodayMeals(date: String): List<MealLogEntity> {
        return mealDao.getTodayMeals(date)
    }

    suspend fun deleteMeal(meal: MealLogEntity) {
        mealDao.deleteMeal(meal)
    }

    suspend fun getTotalCalories(date: String): Int {
        return getTodayMeals(date).sumOf { it.calories }
    }

    suspend fun getTotalProtein(date: String): Double {
        return getTodayMeals(date).sumOf { it.protein }
    }

    suspend fun getAllMeals(): List<MealLogEntity> {
        return mealDao.getAllMeals()
    }

    suspend fun hasMeal(
        mealType: String,
        date: String
    ): Boolean {
        return mealDao.getMealCount(
            mealType,
            date
        ) > 0
    }

    suspend fun getTotalCarbs(date: String): Double {
        return mealDao.getTodayMeals(date).sumOf { it.carbs }
    }

    suspend fun getTotalFat(date: String): Double {
        return mealDao.getTodayMeals(date).sumOf { it.fat }
    }

    suspend fun getLast7Dates(): List<String> {
        return mealDao.getLast7Dates()
    }

    suspend fun getCaloriesByDate(date: String): Int {
        return mealDao.getCaloriesByDate(date) ?: 0
    }

    suspend fun getProteinByDate(date: String): Double {
        return mealDao.getProteinByDate(date) ?: 0.0
    }

    suspend fun replaceMeals(meals: List<MealLogEntity>) {
        mealDao.deleteAllMeals()
        mealDao.insertMeals(meals)
    }
}