package com.example.murmeltier

import android.app.Activity
import android.support.v7.view.ContextThemeWrapper
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ToDoListAdapter(context: Activity, private val toDos: ArrayList<ToDo>) :
    ArrayAdapter<ToDo>(context, R.layout.todo_list, toDos) {

    fun init() {
        this.add(ToDo("First", ToDoState.ACTIVE))
        this.add(ToDo("Second", ToDoState.INACTIVE))
    }

    fun createNew() {
        add(ToDo("Item ${count + 1}", ToDoState.ACTIVE))
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val toDo = toDos[position]
        val view = TextView(ContextThemeWrapper(context, getStyle(toDo.state)))

        view.text = toDo.title

        return view
    }

    private fun getStyle(toDoState: ToDoState): Int {
        return when (toDoState) {
            ToDoState.ACTIVE -> R.style.ToDoItem_Active
            ToDoState.INACTIVE -> R.style.ToDoItem_Inactive
        }
    }


}

