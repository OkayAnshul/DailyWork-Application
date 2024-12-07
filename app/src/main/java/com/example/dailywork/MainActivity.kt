package com.example.dailywork

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.dailywork.database.dwDatabase
import com.example.dailywork.database.dwRepository
import com.example.dailywork.ui.theme.DailyWorkTheme
import com.example.dailywork.viewmodel.dwViewModel
import com.example.dailywork.viewmodel.dwViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Initialize the database
        val database = dwDatabase.getDatabase(applicationContext)
        //Create repository instance
        val repository = dwRepository(database.dailyDao())
        //Create viewmodel
        val viewModel = ViewModelProvider(this,
            dwViewModelFactory(repository)
        )[dwViewModel::class.java]

        //enableEdgeToEdge()
        setContent {
            DailyWorkApplication(viewModel)
        }
    }
}

