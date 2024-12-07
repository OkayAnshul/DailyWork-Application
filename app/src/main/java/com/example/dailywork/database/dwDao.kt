package com.example.dailywork.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface dwDao {
    @Query("Select * from dw_table order by id asc")
    fun getAllTask():Flow<List<Task>>
    @Insert
    suspend fun createTask(task:Task)
    @Update
    suspend fun updateTask(task: Task)
    @Delete
    suspend fun deleteTask(task: Task)
    @Query("select * from dw_table where id=:id ")
    fun getTask(id:Long):Flow<Task>
}