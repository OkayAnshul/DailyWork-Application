package com.example.dailywork.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailywork.database.Task
import com.example.dailywork.database.dwRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class dwViewModel(private val repository: dwRepository) :ViewModel(){

//    var allTask by mutableStateOf<List<Task>>(emptyList())
//
//    init {
//        viewModelScope.launch {
//            repository.getAllTask.collect{
//                task ->
//                allTask = task
//            }
//        }
//    }

     //StateFlow to expose the task list to the UI
    val allTask: StateFlow<List<Task>> = repository.getAllTask
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )


    //This mutableState flow works best with compose ui(jetpack compose)
//But alternative which is using stateIn is also very good
    fun addTask(task:Task){
        viewModelScope.launch {
            repository.createTask(task)
        }
    }
    fun removeTask(task:Task){
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }
    fun updateTask(task:Task){
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }
    fun getTask(id:Long){
        viewModelScope.launch {
            repository.getTask(id)
        }
    }
}
