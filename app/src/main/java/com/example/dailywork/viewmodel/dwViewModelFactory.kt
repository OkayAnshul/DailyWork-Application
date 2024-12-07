package com.example.dailywork.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dailywork.database.dwRepository


//This is very necessary for better dependency injection like repository
//to inject properly with viewmodel otherwise complex app will not work.
//This can be easily taken by hilt and dagger dependency injection methods.

class dwViewModelFactory(private val repository: dwRepository) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(dwViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return dwViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}