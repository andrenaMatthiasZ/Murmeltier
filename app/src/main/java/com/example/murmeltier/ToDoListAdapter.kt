package com.example.murmeltier

import android.app.Activity
import android.graphics.Color
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
        val view = TextView(context)
        val toDo = toDos[position]

        view.text = toDo.title
        view.setTextColor(getColor(toDo.state))

        return view
    }

    private fun getColor(toDoState: ToDoState): Int {
        return when (toDoState) {
            ToDoState.ACTIVE -> Color.BLACK
            ToDoState.INACTIVE -> Color.GRAY
        }
    }

}

