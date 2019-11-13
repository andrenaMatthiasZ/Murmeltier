package com.example.murmeltier

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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

    private fun createToDoList(): TastListAdapter {
        val arrayAdapter = TastListAdapter(this, ArrayList())

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
            R.id.to_do_item -> {
                toDo.state = TaskState.TODO
                toDoListAdapter().notifyDataSetChanged()
                return true
            }
            R.id.done_item -> {
                toDo.state = TaskState.DONE
                toDoListAdapter().notifyDataSetChanged()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun toDoListAdapter() = listView.adapter as TastListAdapter

    private fun rename(
        task: Task
    ) {
        val builder = AlertDialog.Builder(this)
        val editText = EditText(this)
        editText.setText(task.title)

        builder.run {
            setView(editText)

            setPositiveButton(
                "Ok"
            ) { _, _ ->
                run {
                    task.title = editText.text.toString()
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
