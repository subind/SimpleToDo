package com.bridgetree.simpletodo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bridgetree.simpletodo.db.Task
import com.bridgetree.simpletodo.repository.TaskRepo

class MainViewModel : ViewModel() {

    companion object{
        var taskRepo : TaskRepo = TaskRepo.getRepoInstance()
    }

    fun getAllTasks() : LiveData<List<Task>> {
        return taskRepo.getAllTasks()
    }

    fun insertTask(task : Task){
        taskRepo.insertTask(task)
    }

    fun deleteTask(id : Int){
        taskRepo.deleteTask(id)
    }

    fun updateTask(id: Int, task : String){
        taskRepo.updateTask(id, task)
    }


}