package com.example.gharkafit.navigation



sealed class NavRoutes(val route: String) {

    object UserDetail : NavRoutes("user_detail")
    object BMI : NavRoutes("bmi")
    object Goal : NavRoutes("goal")
    object Activity : NavRoutes("activity")
    object Diet : NavRoutes("diet_habbit")
    object ResultBMI : NavRoutes("result_bmi")
    object Home : NavRoutes("home")
    object AddFood : NavRoutes("add_food")
    object DailySummary : NavRoutes("daily_summary")
    object Proges : NavRoutes("proges")

}