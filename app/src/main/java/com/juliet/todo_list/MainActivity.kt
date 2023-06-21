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
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnAdd.setOnClickListener{
            val todoTitle =binding.etTodo.text.toString()
            if (todoTitle.isNotEmpty()){
                val todo =Todo(todoTitle)
                todoAdapter.addTodo(todo)
                binding.etTodo.text.clear()
            }
        }
        binding.btnDelete.setOnClickListener{
//            val deleteList=binding.btnDelete.text.toString()
            todoAdapter.deleteDoneTodos()
        }
    }
    override fun onResume() {
        super.onResume()
        displayList()
    }
    fun displayList(){
        var todo1=Todo("learning kotlin",false)
        var todo2=Todo("revising past questions",true)
        var todo3 =Todo("watching a movie",false)
        var todo4=Todo("creating an app",true)
        var todos= mutableListOf<Todo>(todo1,todo2,todo3,todo4)
       binding.rvTodo.layoutManager=LinearLayoutManager(this)
       var todoAdapter=TodoAdapter(todos)
        binding.rvTodo.adapter=todoAdapter
    }
}