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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.dailywork.R

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
    val Custom = Color(0xFF00334d)
    // Always display the CenterAlignedTopAppBar
    CenterAlignedTopAppBar(
        title = {
            Text(
                currentRoute.toString(),
                fontSize =26.sp,
                fontFamily = FontFamily(Font(R.font.architects_daughter)) // Set custom font
            )
        },
        navigationIcon = navigationIcon, // Pass null or the icon
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Custom, // Set your custom color
            titleContentColor = Color.White, // White color for the title text
            navigationIconContentColor = Color.White // White color for the navigation icon
        )
    )

}

