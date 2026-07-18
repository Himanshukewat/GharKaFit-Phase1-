package com.example.gharkafit.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gharkafit.core.MealParser
import com.example.gharkafit.data.food.FoodEntity
import com.example.gharkafit.data.food.FoodRepository
import com.example.gharkafit.data.meal.MealLogEntity
import com.example.gharkafit.data.meal.MealRepository
import kotlinx.coroutines.launch
import com.example.gharkafit.core.MealAnalyzer
import com.example.gharkafit.data.user.UserEntity
import com.example.gharkafit.data.user.UserRepository
import com.example.gharkafit.model.MealAnalysisResult
import com.example.gharkafit.core.DailySummary
import com.example.gharkafit.core.DailySummaryGenerator
import com.example.gharkafit.core.DateUtils
import com.example.gharkafit.core.MealInsightGenerator


//rename this vm is used for dashboard , mealInsight , progress
class MealViewModel(
    private val mealRepository: MealRepository,
    private val foodRepository: FoodRepository
) : ViewModel() {

    private val mealAnalyzer = MealAnalyzer()
    fun saveMeal(
        meal: MealLogEntity,
        onComplete: () -> Unit
    ) {
        viewModelScope.launch {
            mealRepository.insertMeal(meal)
            onComplete()
        }
    }

    fun getMeals(
        onResult: (List<MealLogEntity>) -> Unit
    ) {
        viewModelScope.launch {
            onResult(
                mealRepository.getTodayMeals(DateUtils.today())
            )
        }
    }

    fun checkMeals() {
        viewModelScope.launch {
            val meals = mealRepository.getTodayMeals(DateUtils.today())
            Log.d("ROOM_MEALS", meals.toString())
        }
    }

    fun checkFood(foodName: String) {
        viewModelScope.launch {
            val food = foodRepository.getFoodByName(foodName)
            Log.d("FOOD_CHECK", food.toString())
        }
    }

    fun analyzeMeal(
        input: String,
        onResult: (MealAnalysisResult) -> Unit
    ) {
        viewModelScope.launch {
            val words = MealParser.parseMeal(input)
            var food: FoodEntity? = null
            for (word in words) {
                food = foodRepository.getFoodByName(word)

                if (food != null) {
                    break
                }
            }
            val result = mealAnalyzer.analyze(food)
            onResult(result)
        }
    }

    fun getTotalCalories(onResult: (Int) -> Unit) {
        viewModelScope.launch {
            onResult(
                mealRepository.getTotalCalories(DateUtils.today())
            )
        }
    }

    fun getTotalProtein(onResult: (Double) -> Unit) {
        viewModelScope.launch {
            onResult(
                mealRepository.getTotalProtein(DateUtils.today())
            )
        }
    }

    fun getAllMeals(onResult: (List<MealLogEntity>) -> Unit) {
        viewModelScope.launch {
            onResult(
                mealRepository.getAllMeals()
            )
        }
    }

    fun hasMeal(
        mealType: String,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            onResult(
                mealRepository.hasMeal(mealType)
            )
        }
    }

    fun getUser(
        repository: UserRepository,
        onResult: (UserEntity?) -> Unit
    ) {
        viewModelScope.launch {
            val user = repository.getUser()
            Log.d("USER_DATA", user.toString())
            onResult(user)
        }
    }

    fun updateUser(
        repository: UserRepository,
        user: UserEntity,
        onComplete: () -> Unit
    ) {
        viewModelScope.launch {
            repository.updateUser(user)
            onComplete()
        }
    }

    private val dailySummaryGenerator = DailySummaryGenerator()
    fun generateSummary(
        calories: Int,
        calorieTarget: Int,
        protein: Double,
        proteinTarget: Double
    ): DailySummary {

        return dailySummaryGenerator.generate(
            calories = calories,
            calorieTarget = calorieTarget,
            protein = protein,
            proteinTarget = proteinTarget
        )
    }

    private val mealInsightGenerator = MealInsightGenerator()

    fun getTotalCarbs(
        onResult: (Double) -> Unit) {
        viewModelScope.launch {
            onResult(
                mealRepository.getTotalCarbs(DateUtils.today())
            )
        }
    }

    fun getTotalFat(
        onResult: (Double) -> Unit ){
        viewModelScope.launch {
            onResult(
                mealRepository.getTotalFat(DateUtils.today())
            )
        }
    }

    fun getMealCount(onResult: (Int) -> Unit) {
        viewModelScope.launch {
            onResult(
                mealRepository.getAllMeals().size
            )
        }
    }

    fun getWeeklyStats(
        user: UserEntity,
        onResult: (Pair<Int, Int>) -> Unit
    ) {
        viewModelScope.launch {
            var proteinDays = 0
            var calorieDays = 0
            val dates = mealRepository.getLast7Dates()
            for (date in dates) {
                val calories = mealRepository.getCaloriesByDate(date)
                val protein = mealRepository.getProteinByDate(date)
                if (calories <= user.targetCalories) {
                    calorieDays++
                }
                if (protein >= user.targetProtein) {
                    proteinDays++
                }
            }
            onResult(Pair(
                    proteinDays,
                    calorieDays
                )
            )
        }
    }
}