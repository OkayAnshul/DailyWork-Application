package com.example.dailywork.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface dwDao {
    @Query("SELECT * FROM dw_table ORDER BY id ASC")
    fun getAllTask(): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    // Suspend function to get a single task by ID (if required)
    @Query("SELECT * FROM dw_table WHERE id = :id")
    suspend fun getTask(id: Long): Task?

    // New function to observe a single task dynamically as a Flow
//    @Query("SELECT * FROM dw_table WHERE id = :id")
//    fun getTask(id: Long): Flow<Task?>
}
