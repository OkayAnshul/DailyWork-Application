package com.example.dailywork.database

import kotlinx.coroutines.flow.Flow

class dwRepository(private val dao: dwDao) {

    val getAllTask: Flow<List<Task>> = dao.getAllTask()

    // Changed this to return a Flow<Task?> for observing the task dynamically
    fun getTaskFlow(id: Long): Flow<Task?> {
        return dao.getTaskFlow(id)
    }

    suspend fun createTask(task: Task) {
        dao.createTask(task)
    }

    suspend fun deleteTask(task: Task) {
        dao.deleteTask(task)
    }

    suspend fun updateTask(task: Task) {
        dao.updateTask(task)
    }
}
