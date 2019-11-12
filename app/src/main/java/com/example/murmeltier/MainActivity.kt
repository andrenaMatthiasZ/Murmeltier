package com.example.murmeltier

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.EditText
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        listView = findViewById(R.id.list)

        listView.adapter = createToDoList()
        registerForContextMenu(listView)
    }

    private fun createToDoList(): ToDoListAdapter {
        val arrayAdapter = ToDoListAdapter(this, ArrayList())

        arrayAdapter.init()
        fab.setOnClickListener { run { arrayAdapter.createNew() } }

        return arrayAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        val toDo = toDoListAdapter().getItem((item.menuInfo as AdapterContextMenuInfo).position)!!

        return when (item.itemId) {
            R.id.rename_item -> {
                rename(toDo)
                return true
            }
            R.id.delete_item -> {
                toDoListAdapter().remove(toDo)
                return true
            }
            R.id.activate_item ->
                true
            R.id.deactivate_item ->
                true
            else -> super.onContextItemSelected(item)
        }
    }

    private fun toDoListAdapter() = listView.adapter as ToDoListAdapter

    private fun rename(
        toDo: ToDo
    ) {
        val builder = AlertDialog.Builder(this)
        val editText = EditText(this)

        builder.run {
            setView(editText)

            setPositiveButton(
                "Ok"
            ) { _, _ ->
                run {
                    toDo.title = editText.text.toString()
                    toDoListAdapter().notifyDataSetChanged()
                }
            }
            setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

            show()
        }
    }


    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_item, menu)
    }

}
