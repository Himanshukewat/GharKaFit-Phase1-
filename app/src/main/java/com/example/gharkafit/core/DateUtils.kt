package com.example.gharkafit.core

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {

    fun today(): String {
        return SimpleDateFormat(
            "yyyy-MM-dd",
            Locale.getDefault()
        ).format(Date())
    }
}