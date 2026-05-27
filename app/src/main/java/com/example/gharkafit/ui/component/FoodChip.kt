package com.example.gharkafit.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FoodChip(
    text: String
) {

    Surface(
        shape = RoundedCornerShape(18.dp),
        color = Color(0xFFE8F5E9)
    ) {

        Text(
            text = text,
            color = Color(0xFF1B5E20),
            modifier = Modifier.padding(
                horizontal = 18.dp,
                vertical = 10.dp
            )
        )
    }
}