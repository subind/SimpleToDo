package com.bridgetree.simpletodo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bridgetree.simpletodo.R
import com.bridgetree.simpletodo.db.Task
import kotlinx.android.synthetic.main.item_view_task.view.*

class TaskAdapter(var taskList : List<Task>): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_view_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        var task : Task = taskList.get(position)
        holder.itemView.apply {
            item_taskname.text = task.taskName
            item_taskid.text = task.id.toString()
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}