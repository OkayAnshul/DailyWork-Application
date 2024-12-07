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
import androidx.navigation.NavHostController

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CustomTopBar(appTitle: String, navHostController: NavHostController) {
    // Check if the current screen is not the Home screen
    val navigationIcon: @Composable (() -> Unit) = {
        if (navHostController.currentBackStackEntry?.destination?.route != Screen.HomeScreen.route)
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
        title = { Text(appTitle) },
        navigationIcon = navigationIcon, // Pass null or the icon
//            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = ,
//                titleContentColor = ,
//                navigationIconContentColor = ,
        //               )
    )
}

