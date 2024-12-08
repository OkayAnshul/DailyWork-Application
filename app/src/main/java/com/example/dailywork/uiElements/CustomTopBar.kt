package com.example.dailywork.uiElements

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CustomTopBar(navHostController: NavHostController) {
    // Observe the current route

    val backStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val isHomeScreen = currentRoute == Screen.HomeScreen.route

    val navigationIcon: @Composable (() -> Unit) = {
        if (!isHomeScreen)
        // Create a back arrow icon button
        {
            IconButton(onClick = { navHostController.popBackStack() }) {
                Icon(imageVector = Icons.AutoMirrored.Sharp.ArrowBack, contentDescription = "Back")
            }
        } else {
            IconButton(onClick = {}) {
            }
        }
    }
    // Always display the CenterAlignedTopAppBar
    CenterAlignedTopAppBar(
        title = { Text(currentRoute.toString()) },
        navigationIcon = navigationIcon, // Pass null or the icon
            colors = TopAppBarDefaults.
            centerAlignedTopAppBarColors(containerColor =
            Color(0xFF2A3C3C),
               titleContentColor = Color.White,
                navigationIconContentColor = Color.White,
                )
    )
}

