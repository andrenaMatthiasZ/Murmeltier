package com.example.murmeltier

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper

class TastListAdapter(context: Activity, private val tasks: ArrayList<Task>) :
    ArrayAdapter<Task>(context, R.layout.todo_list, tasks) {

    fun init() {
        this.add(Task("First", TaskState.TODO))
        this.add(Task("Second", TaskState.DONE))
    }

    fun createNew() {
        add(Task("Item ${count + 1}", TaskState.TODO))
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val toDo = tasks[position]
        val view = TextView(ContextThemeWrapper(context, getStyle(toDo.state)))

        view.text = toDo.title

        return view
    }

    private fun getStyle(taskState: TaskState): Int {
        return when (taskState) {
            TaskState.TODO -> R.style.ToDoItem_ToDo
            TaskState.DONE -> R.style.ToDoItem_Done
        }
    }

    fun setToDone(position: Int) {
        getItem(position)!!.state = TaskState.DONE

        notifyDataSetChanged()
    }

    fun setToToDo(position: Int) {
        getItem(position)!!.state = TaskState.TODO

        notifyDataSetChanged()
    }

    fun setText(position: Int, newTitle: String) {
        getItem(position)!!.title = newTitle

        notifyDataSetChanged()
    }


}

