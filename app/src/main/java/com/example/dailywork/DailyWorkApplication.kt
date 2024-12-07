package com.example.dailywork

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dailywork.uiElements.CustomTopBar
import com.example.dailywork.uiElements.EditScreen
import com.example.dailywork.uiElements.HomeScreen
import com.example.dailywork.uiElements.Screen
import com.example.dailywork.viewmodel.dwViewModel

@Composable
fun DailyWorkApplication(viewModel: dwViewModel) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            val currentRoute = navController.currentBackStackEntry?.destination?.route
            CustomTopBar(
                 currentRoute ?: "Daily Work", // Default title if route is null
                 navController
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.EditScreen.route) },
                shape = FloatingActionButtonDefaults.shape
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            // HomeScreen: Display tasks
            composable(Screen.HomeScreen.route) {
                HomeScreen(
                    modifier = Modifier.padding(innerPadding),
                    viewModel = viewModel // Pass viewModel to HomeScreen
                )
            }
            // EditScreen: Add/Edit a task
            composable(Screen.EditScreen.route) {
                EditScreen(
                    modifier = Modifier.padding(innerPadding),
                    task = null, // Pass a task if editing, null for new tasks
                    onSave = { updatedTask ->
                        // Save task logic here
                        viewModel.updateTask(updatedTask)
                        navController.popBackStack() // Navigate back after saving
                    },
                    onCancel = {
                        navController.popBackStack() // Navigate back on cancel
                    }
                )
            }
        }
    }
}
