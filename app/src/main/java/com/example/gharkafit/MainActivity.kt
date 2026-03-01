package com.example.gharkafit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.gharkafit.ui.MainApp
import com.example.gharkafit.ui.theme.GharKaFitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GharKaFitTheme {
                MainApp()
            }
        }
    }
}
