//package com.example.gharkafit.ui.screen.dietHabit
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.platform.LocalContext
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.gharkafit.data.MainDatabase
//import com.example.gharkafit.model.DietHabit
//import com.example.gharkafit.ui.component.DietCard
//
//@Composable
//fun DietHabit(
//    onContinue: () -> Unit
//) {
//
//    val context = LocalContext.current
//    val db = MainDatabase.getDatabase(context)
//
//    val viewModel: DietHabitVM = viewModel(
//        factory = DietHabitVMF(db.userDao())
//    )
//
//    var selectedHabit by remember { mutableStateOf<DietHabit?>(null) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(20.dp),
//
//        verticalArrangement = Arrangement.spacedBy(20.dp)
//    ) {
//
//        Text(
//            text = "Your Current Diet",
//            style = MaterialTheme.typography.headlineSmall
//        )
//
//        Text(
//            text = "No judgment. We’ll improve step by step 😊",
//            style = MaterialTheme.typography.bodyMedium,
//            color = MaterialTheme.colorScheme.onSurfaceVariant
//        )
//
//        DietCard(
//            title = "Mostly Processed Food",
//            description = "Fast food, packaged snacks, sugary drinks",
//            isSelected = selectedHabit == DietHabit.PROCESSED
//        ) {
//            selectedHabit = DietHabit.PROCESSED
//        }
//
//        DietCard(
//            title = "Mixed Diet",
//            description = "Some home food, some outside food",
//            isSelected = selectedHabit == DietHabit.MIXED
//        ) {
//            selectedHabit = DietHabit.MIXED
//        }
//
//        DietCard(
//            title = "Mostly Home Food",
//            description = "Roti, sabzi, dal, rice, ghar ka khana",
//            isSelected = selectedHabit == DietHabit.HOME
//        ) {
//            selectedHabit = DietHabit.HOME
//        }
//
//        Spacer(modifier = Modifier.weight(1f))
//
//        Button(
//            onClick = {
//                selectedHabit?.let {
//                    viewModel.saveHabit(it.name)
//                }
//                onContinue()
//            },
//
//            enabled = selectedHabit != null,
//
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("See My Plan")
//        }
//    }
//}