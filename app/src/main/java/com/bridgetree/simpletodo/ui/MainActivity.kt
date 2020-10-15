package com.bridgetree.simpletodo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    val TAG = MainActivity::class.java.simpleName
    var mainViewModel : MainViewModel? = null
    lateinit var taskList : List<Task>
    var taskAdapter : TaskAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAdd.setOnClickListener(this)
        btnUpdate.setOnClickListener(this)
        btnDelete.setOnClickListener(this)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        populateTaskList()

        var linearLayoutManager : LinearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        taskAdapter = TaskAdapter(listOf())
        recyclerView.adapter = taskAdapter
    }

    override fun onClick(view: View?) {
        when(view){
            //Make id column optional :- https://stackoverflow.com/a/55097140/9793057
            btnAdd -> mainViewModel!!.insertTask(Task(when(et_task_id.text.toString().isBlank()){
                true -> null
                false -> et_task_id.text.toString().toInt()
            }, et_Task.text.toString()))

            btnUpdate -> mainViewModel!!.updateTask(et_task_id.text.toString().toInt(), et_Task.text.toString())

            btnDelete -> mainViewModel!!.deleteTask(et_task_id.text.toString().toInt())
        }
    }

    private fun populateTaskList(){
        mainViewModel!!.getAllTasks().observe(this, object : Observer<List<Task>> {
            override fun onChanged(t: List<Task>?) {
                if (t != null) {
                    taskList = t
                    //Recycler view reset
                    taskAdapter = TaskAdapter(taskList)
                    recyclerView.adapter = taskAdapter
                    //Clear the fields
                    et_task_id.text.clear()
                    et_Task.text.clear()
                }
            }
        })
    }

}