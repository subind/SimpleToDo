package com.bridgetree.simpletodo.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)


    @Query("DELETE FROM task_table WHERE id = :id")
    fun deleteTask(id: Int)


    @Query("UPDATE task_table SET taskName = :task WHERE id = :id")
    fun updateTask(id: Int, task: String)



    @Query("SELECT * FROM task_table")
    fun getAllTasks() : LiveData<List<Task>>

}