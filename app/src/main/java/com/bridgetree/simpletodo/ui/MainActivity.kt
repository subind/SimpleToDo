package com.bridgetree.simpletodo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.bridgetree.simpletodo.R
import com.bridgetree.simpletodo.adapter.TaskAdapter
import com.bridgetree.simpletodo.db.Task
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var mainViewModel : MainViewModel? = null
    lateinit var taskList : List<Task>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        populateTaskList()

        var linearLayoutManager : LinearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        var taskAdapter : TaskAdapter = TaskAdapter(listOf())
        recyclerView.adapter = taskAdapter
    }

    override fun onClick(view: View?) {
        when(view){
            btnAdd -> mainViewModel!!.insertTask(Task(et_task_id.text.toString().toInt(), et_Task.text.toString()))

            btnUpdate -> mainViewModel!!.updateTask(et_task_id.text.toString().toInt(), et_Task.text.toString())

            btnDelete -> mainViewModel!!.deleteTask(et_task_id.text.toString().toInt())
        }
    }

    private fun populateTaskList(){
        mainViewModel!!.getAllTasks().observe(this, object : Observer<List<Task>> {
            override fun onChanged(t: List<Task>?) {
                if (t != null) {
                    taskList = t
                }
            }
        })
    }

}