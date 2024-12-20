package com.example.dailywork.database

import kotlinx.coroutines.flow.Flow

class dwRepository(private val dao: dwDao) {

    val getAllTask: Flow<List<Task>> = dao.getAllTask()

     fun getTask(id: Long):Flow<Task> {
        return dao.getTask(id)
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
