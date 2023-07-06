package com.juliet.todo_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.juliet.todo_list.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.btnAdd.setOnClickListener {
            val todoTitle = binding.etTodo.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                binding.etTodo.text.clear()
            }
        }
        binding.btnDelete.setOnClickListener {
//            val deleteList=binding.btnDelete.text.toString()
            todoAdapter.deleteDoneTodos()
        }
    }

    override fun onResume() {
        super.onResume()
        initList()
        displayList()


    }

    private fun makeToDoList(): List<Todo> {
        val todo1 = Todo("learning kotlin", false)
        val todo2 = Todo("revising past questions", true)
        val todo3 = Todo("watching a movie", false)
        val todo4 = Todo("creating an app", true)
        return mutableListOf(todo1, todo2, todo3, todo4)
    }

    private fun initList() {
        val todoList = makeToDoList()
        todoAdapter = TodoAdapter(todoList.toMutableList())
    }

    private fun displayList() {
        binding.rvTodo.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = todoAdapter
        }
    }
}
