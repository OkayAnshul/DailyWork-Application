package com.example.dailywork.uiElements

sealed class Screen(val route:String){
    object HomeScreen:Screen("DailyWork on Your List")
    object EditScreen:Screen("Edit or Add ?")
}
