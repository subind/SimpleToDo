package com.bridgetree.simpletodo.repository

import androidx.lifecycle.LiveData
import com.bridgetree.simpletodo.db.Task
import com.bridgetree.simpletodo.db.TaskDao
import com.bridgetree.simpletodo.db.TaskDatabase

class TaskRepo {

    var databaseInstance : TaskDatabase? = null
    var taskDao : TaskDao? = null


    constructor(){
        databaseInstance = TaskDatabase.getDatabase()
        taskDao = databaseInstance?.getTaskDao()
    }

    companion object{
        var repoInstanceX : TaskRepo? = null

        fun getRepoInstance() : TaskRepo{
            if(repoInstanceX == null){
                repoInstanceX = TaskRepo()
            }
            return repoInstanceX as TaskRepo
        }

    }

    fun getAllTasks() : LiveData<List<Task>>{
        return taskDao!!.getAllTasks()
    }

    //In-order to execute a function using co-routine it has to be appended with 'suspend' keyword
    suspend fun insertTask(task : Task){
        taskDao!!.insertTask(task)
    }

    suspend fun deleteTask(id : Int){
        taskDao!!.deleteTask(id)
    }

    suspend fun updateTask(id: Int, task : String){
        taskDao?.updateTask(id, task)
    }

}