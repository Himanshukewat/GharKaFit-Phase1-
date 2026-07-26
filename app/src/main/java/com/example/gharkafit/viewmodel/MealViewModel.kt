package com.example.gharkafit.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gharkafit.core.MealParser
import com.example.gharkafit.data.food.FoodEntity
import com.example.gharkafit.data.food.FoodRepository
import com.example.gharkafit.data.meal.MealLogEntity
import com.example.gharkafit.data.meal.MealRepository
import com.example.gharkafit.ai.GeminiRepository
import kotlinx.coroutines.launch
import com.example.gharkafit.core.MealAnalyzer
import com.example.gharkafit.data.user.UserEntity
import com.example.gharkafit.data.user.UserRepository
import com.example.gharkafit.model.MealAnalysisResult
import com.example.gharkafit.core.DailySummary
import com.example.gharkafit.core.DailySummaryGenerator
import com.example.gharkafit.core.DateUtils
import com.example.gharkafit.core.MealInsightGenerator
import com.example.gharkafit.data.remote.FirestoreRepository
import com.example.gharkafit.data.remote.MealFirestoreRepository
import com.example.gharkafit.model.WeeklyStats


//rename this vm is used for dashboard , mealInsight , progress
class MealViewModel(
    private val mealRepository: MealRepository,
    private val foodRepository: FoodRepository,
    private val firestoreRepository: FirestoreRepository,
    private val mealFirestoreRepository: MealFirestoreRepository
) : ViewModel() {

    private val mealAnalyzer = MealAnalyzer()
    private val geminiRepository = GeminiRepository()
    fun saveMeal(
        meal: MealLogEntity,
        onComplete: () -> Unit
    ) {
        viewModelScope.launch {

            // Room Save
//            mealRepository.insertMeal(meal)

            // Firestore Save
            mealFirestoreRepository.saveMeal(
                meal = meal,
                onSuccess = { updatedMeal ->
                    viewModelScope.launch {
                        mealRepository.insertMeal(updatedMeal)
                        onComplete()
                    }
                },
                onError = {
                    Log.e("MEAL_SYNC", it)
                    onComplete()
                }
            )
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
                mealRepository.hasMeal(
                    mealType,
                    DateUtils.today()
                )
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
            // Room update
            repository.updateUser(user)
            // Firestore update
            firestoreRepository.saveUser(
                user = user,
                onSuccess = {
                    onComplete()
                },
                onError = {
                    Log.e("FIRESTORE", it)
                }
            )
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
                    mealRepository.getLast7DaysMealCount()
                )
            }
    }

    fun getWeeklyStats(
        user: UserEntity,
        onResult: (WeeklyStats) -> Unit
    ) {
        viewModelScope.launch {
            var proteinDays = 0
            var calorieDays = 0
            val dates = mealRepository.getLast7Dates()
            for (date in dates) {
                val calories = mealRepository.getCaloriesByDate(date)
                val protein = mealRepository.getProteinByDate(date)
                val minCalories = (user.targetCalories * 0.9).toInt()
                val maxCalories = (user.targetCalories * 1.1).toInt()
                if (calories in minCalories..maxCalories) {
                    calorieDays++
                }
                if (protein >= user.targetProtein) {
                    proteinDays++
                }
            }
            onResult(
                WeeklyStats(
                    proteinDays = proteinDays,
                    calorieDays = calorieDays,
                    trackedDays = dates.size
                )
            )
        }
    }

    fun analyzeMealWithAI(
        input: String,
        onResult: (com.example.gharkafit.ai.MealAnalysisResult) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val result = geminiRepository.analyzeMeal(input)
                onResult(result)
            } catch (e: Exception) {
                Log.e("GEMINI", "Error", e)
                val message = when {
                    e.message?.contains("429") == true ->
                        "AI usage limit reached. Please try again later."
                    e.message?.contains("Unable to resolve host") == true ->
                        "No internet connection."
                    else ->
                        "Unable to analyze meal."
                }
                onError(message)
            }
        }
    }

    fun deleteMeal(
        meal: MealLogEntity,
        onComplete: () -> Unit
    ) {
        mealFirestoreRepository.deleteMeal(
            meal = meal,
            onSuccess = {
                viewModelScope.launch {
                    mealRepository.deleteMeal(meal)
                    onComplete()
                }
            },
            onError = {
                Log.e("DELETE_MEAL", it)
            }
        )
    }

    fun updateMeal(
        meal: MealLogEntity,
        onComplete: () -> Unit
    ) {
        Log.d("UPDATE_MEAL", "Called")

        mealFirestoreRepository.updateMeal(
            meal = meal,
            onSuccess = {
                Log.d("UPDATE_MEAL", "Firestore Success")

                viewModelScope.launch {
                    mealRepository.updateMeal(meal)
                    Log.d("UPDATE_MEAL", "Room Success")

                    onComplete()
                }
            },
            onError = {
                Log.e("UPDATE_MEAL", it)
            }
        )
    }
}