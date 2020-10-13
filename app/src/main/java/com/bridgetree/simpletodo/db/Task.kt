package com.bridgetree.simpletodo.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var taskName : String
) {
}