package com.example.dailywork.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalTime


@Entity(tableName ="dw_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val title:String,
    val description:String,
    val time:LocalTime)