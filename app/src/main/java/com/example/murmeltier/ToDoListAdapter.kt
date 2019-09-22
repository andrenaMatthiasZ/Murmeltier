package com.example.murmeltier

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ToDoListAdapter(context: Activity, private val toDos: ArrayList<ToDo>) :
    ArrayAdapter<ToDo>(context,R.layout.todo_list, toDos) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = TextView(context)

        view.text = toDos[position].title

        return view
    }


}

