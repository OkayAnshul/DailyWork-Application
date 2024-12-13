package com.example.dailywork.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailywork.database.Task
import com.example.dailywork.database.dwRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class dwViewModel(private val repository: dwRepository) : ViewModel() {

    var currentTitle by mutableStateOf("")

    fun ChangeTitle(taskId:Long){
        if(taskId>0)
            currentTitle="Wanna Edit?"
        else
            currentTitle="Add Something?"
    }
    // StateFlow for the task list
    val allTask: StateFlow<List<Task>> = repository.getAllTask
        .distinctUntilChanged()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )

    // Mutable state for selected task
    private val _selectedTask = mutableStateOf<Task?>(null)
    val selectedTask: Task? get() = _selectedTask.value

    // Add a new task
    fun addTask(task: Task) {
        viewModelScope.launch {
            try {
                repository.createTask(task)
            } catch (e: Exception) {
                // Log or handle error
            }
        }
    }

    // Remove a task
    fun removeTask(task: Task) {
        viewModelScope.launch {
            try {
                repository.deleteTask(task)
            } catch (e: Exception) {
                // Log or handle error
            }
        }
    }

    // Edit an existing task
    fun editTask(task: Task) {
        viewModelScope.launch {
            try {
                repository.updateTask(task)
            } catch (e: Exception) {
                // Log or handle error
            }
        }
    }

    // Update task status
    fun updateTaskStatus(task: Task, isDone: Boolean) {
        viewModelScope.launch {
            try {
                repository.updateTask(task.copy(isDone = isDone))
            } catch (e: Exception) {
                // Log or handle error
            }
        }
    }

    // Fetch a task by ID and update the state
    fun getTask(id:Long):Flow<Task>
    {
        return repository.getTask(id)
    }
//    fun getTaskState(taskId: Long): StateFlow<Task?> {
//        return repository.getTaskFlow(taskId).stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.Lazily,
//            initialValue = null
//        )
//    }

}
