package com.example.gharkafit.core

object MealParser {

    fun parseMeal(input: String): List<String> {
        val ignoreWords = setOf(
            "1", "2", "3", "4", "5",
            "a", "an",
            "bowl", "plate", "glass", "cup",
            "kg", "gm", "g", "ml",
            "ki", "ka", "ke",
            "sabji", "sabzi",
            "of", "and"
        )

        return input
            .lowercase()
            .replace(",", " ")
            .split(" ")
            .filter {
                it.isNotBlank() &&
                        it !in ignoreWords &&
                        it.toDoubleOrNull() == null
            }
    }
}