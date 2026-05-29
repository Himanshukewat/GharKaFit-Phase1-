//package com.example.gharkafit.ui.component
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.unit.dp
//
//@Composable
//fun QuantitySelector(
//    quantity: Int,
//    onIncrease: () -> Unit,
//    onDecrease: () -> Unit
//) {
//
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//
//        Text(
//            text = "Quantity",
//            style = MaterialTheme.typography.titleMedium
//        )
//
//        Row(verticalAlignment = Alignment.CenterVertically) {
//
//            OutlinedButton(onClick = onDecrease) {
//                Text("-")
//            }
//
//            Text(
//                text = quantity.toString(),
//                modifier = Modifier.padding(horizontal = 16.dp),
//                style = MaterialTheme.typography.titleMedium
//            )
//
//            OutlinedButton(onClick = onIncrease) {
//                Text("+")
//            }
//        }
//    }
//}