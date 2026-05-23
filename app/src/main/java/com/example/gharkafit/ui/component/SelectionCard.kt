package com.example.gharkafit.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SelectionCard(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier
) {

    Card(
        modifier = Modifier.clickable {
            onClick()
        },
        colors = CardDefaults.cardColors(
            containerColor =
                if (isSelected)
                    Color(0xFF4CAF50)
                else
                    Color(0xFFE8F5E9)
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.padding(
                horizontal = 20.dp,
                vertical = 14.dp
            )
        )
    }
}



