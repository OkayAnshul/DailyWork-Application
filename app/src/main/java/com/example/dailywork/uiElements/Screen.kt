package com.example.dailywork.uiElements

sealed class Screen(val route:String){
    object HomeScreen:Screen("DailyWork")
    object EditScreen:Screen("Add or Edit ?")
}
