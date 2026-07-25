package com.example.gharkafit.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gharkafit.data.food.FoodDao
import com.example.gharkafit.data.food.FoodEntity
import com.example.gharkafit.data.meal.MealDao
import com.example.gharkafit.data.meal.MealLogEntity
import com.example.gharkafit.data.user.UserDao
import com.example.gharkafit.data.user.UserEntity

@Database(
    entities = [
        UserEntity::class,
        FoodEntity::class,
        MealLogEntity::class
    ],
    version = 7,
    exportSchema = false
)
abstract class MainDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun foodDao(): FoodDao
    abstract fun mealDao(): MealDao

    companion object {

        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getDatabase(context: Context): MainDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDatabase::class.java,
                    "gharkafit_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}