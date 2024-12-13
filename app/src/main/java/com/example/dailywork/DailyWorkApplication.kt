package com.example.dailywork

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dailywork.uiElements.CustomTopBar
import com.example.dailywork.uiElements.EditScreen
import com.example.dailywork.uiElements.HomeScreen
import com.example.dailywork.uiElements.Screen
import com.example.dailywork.viewmodel.dwViewModel

@Composable
fun DailyWorkApplication(viewModel: dwViewModel) {
    val navController = rememberNavController()
    val currentRoute=navController.currentBackStackEntryAsState().value?.destination?.route
    val isHomeScreen = currentRoute == Screen.HomeScreen.route

        if (isHomeScreen)
        viewModel.currentTitle ="DailyWork"

    Scaffold(
        topBar = {
            CustomTopBar(viewModel.currentTitle,
                navHostController = navController)
        },
        containerColor = Color(0xFF002233),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if(isHomeScreen) {
                        val taskId=0L
                        viewModel.ChangeTitle(taskId)
                        navController.navigate(Screen.EditScreen.route+"/${taskId}")
                    }

                          },
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
                    viewModel = viewModel,
                    onEdit ={taskId ->
                        viewModel.ChangeTitle(taskId)
                        navController.navigate(Screen.EditScreen.route+"/$taskId")
                    }
                )
            }
            // EditScreen: Add/Edit a task
            composable(route = Screen.EditScreen.route+"/{taskId}",
                arguments = listOf(navArgument("taskId"){type= NavType.LongType})
            ){
                backstackEntry->
                val taskId= backstackEntry.arguments?.getLong("taskId")?: -1
                EditScreen(taskId = taskId,
                    onCancel ={navController.popBackStack()},
                 //   onSave ={viewModel.addTask()})
                    viewModel = viewModel,
                    navHostController = navController)
            }
        }
    }
}



