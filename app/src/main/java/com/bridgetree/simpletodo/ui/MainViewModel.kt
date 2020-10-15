package com.bridgetree.simpletodo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bridgetree.simpletodo.db.Task
import com.bridgetree.simpletodo.repository.TaskRepo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    companion object{
        var taskRepo : TaskRepo = TaskRepo.getRepoInstance()
    }

    fun getAllTasks() : LiveData<List<Task>> {
        return taskRepo.getAllTasks()
    }

    fun insertTask(task : Task) = GlobalScope.launch { taskRepo.insertTask(task) }

    fun deleteTask(id : Int) = GlobalScope.launch { taskRepo.deleteTask(id) }

    fun updateTask(id: Int, task : String) = GlobalScope.launch { taskRepo.updateTask(id, task) }

}