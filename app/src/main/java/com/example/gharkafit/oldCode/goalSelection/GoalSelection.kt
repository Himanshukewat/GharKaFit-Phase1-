//package com.example.gharkafit.ui.screen.goalSelection
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.platform.LocalContext
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.gharkafit.data.MainDatabase
//import com.example.gharkafit.model.Goal
//import com.example.gharkafit.ui.component.GoalCard
//
//@Composable
//fun GoalSelection(
//    onGoalSelected: () -> Unit
//) {
//
//    val context = LocalContext.current
//    val db = MainDatabase.getDatabase(context)
//
//    val viewModel: GoalSelectionVM = viewModel(
//        factory = GoalSelectionVMF(db.userDao())
//    )
//
//    var selectedGoal by remember { mutableStateOf<Goal?>(null) }
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
//            text = "Choose Your Goal",
//            style = MaterialTheme.typography.headlineSmall
//        )
//
//        Text(
//            text = "We’ll personalize your diet step by step.",
//            style = MaterialTheme.typography.bodyMedium,
//            color = MaterialTheme.colorScheme.onSurfaceVariant
//        )
//
//        GoalCard(
//            title = "Fat Loss",
//            description = "Lose fat gradually without starving",
//            isSelected = selectedGoal == Goal.FAT_LOSS
//        ) {
//            selectedGoal = Goal.FAT_LOSS
//        }
//
//        GoalCard(
//            title = "Muscle Gain",
//            description = "Build strength with ghar ka khana",
//            isSelected = selectedGoal == Goal.MUSCLE_GAIN
//        ) {
//            selectedGoal = Goal.MUSCLE_GAIN
//        }
//
//        GoalCard(
//            title = "Stay Fit",
//            description = "Maintain a healthy & active lifestyle",
//            isSelected = selectedGoal == Goal.MAINTAIN
//        ) {
//            selectedGoal = Goal.MAINTAIN
//        }
//
//        Spacer(modifier = Modifier.weight(1f))
//
//        Button(
//            onClick = {
//                selectedGoal?.let {
//                    viewModel.saveGoal(it.name)
//                }
//                onGoalSelected()
//            },
//
//            enabled = selectedGoal != null,
//
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Continue")
//        }
//    }
//}