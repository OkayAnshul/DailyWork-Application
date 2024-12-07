package com.example.dailywork.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities =[Task::class],
    version = 1,
    exportSchema = false)
abstract class dwDatabase:RoomDatabase() {
    //Define Dao
    abstract fun dailyDao():dwDao

    companion object{
        @Volatile
        private var INSTANCE: dwDatabase?=null

        fun getDatabase(context:Context):dwDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    dwDatabase::class.java,
                    "dw_database"
                    ).build()
                INSTANCE=instance
                instance
            }
        }
    }
}