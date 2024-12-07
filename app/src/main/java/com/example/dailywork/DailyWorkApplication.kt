package com.example.dailywork

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dailywork.uiElements.CustomTopBar
import com.example.dailywork.uiElements.EditScreen
import com.example.dailywork.uiElements.HomeScreen
import com.example.dailywork.uiElements.Screen

@Composable
fun DailyWorkApplication(){
    val navController= rememberNavController()
    Scaffold(topBar ={ CustomTopBar(navController.currentBackStackEntry?.destination?.route.toString(),
        navController) },
        floatingActionButton = {
            FloatingActionButton(onClick = {navController.navigate(Screen.EditScreen.route)},
                shape =FloatingActionButtonDefaults.shape,
//                containerColor =,
//                contentColor = ,
            ) { }
        }
    ){

            innerpadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreen.route
        ) {

            composable(Screen.HomeScreen.route) {
                HomeScreen(Modifier.padding(innerpadding))
            }
            composable(Screen.EditScreen.route) {
                EditScreen(Modifier.padding(innerpadding))
            }
        }
    }
}
