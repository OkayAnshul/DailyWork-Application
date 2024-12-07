package com.example.dailywork.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalTime


@Entity(tableName ="dw_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0,
    val title:String,
    val description:String,
    var isDone:Boolean=false,
//    val time:LocalTime
)