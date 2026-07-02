package com.example.gharkafit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.example.gharkafit.data.MainDatabase
import com.example.gharkafit.data.food.FoodRepository
import com.example.gharkafit.ui.MainApp
import com.example.gharkafit.ui.theme.GharKaFitTheme
import com.example.gharkafit.viewmodel.FoodSeeder
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = MainDatabase.getDatabase(this)
        val foodRepository = FoodRepository(database.foodDao())
        val foodSeeder = FoodSeeder(foodRepository)
        lifecycleScope.launch {
            foodSeeder.seed()
            setContent {
                GharKaFitTheme {
                    MainApp()
                }
            }
        }
    }
}
