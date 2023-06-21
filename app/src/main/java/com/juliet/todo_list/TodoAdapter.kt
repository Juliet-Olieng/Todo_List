package com.juliet.todo_list

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.juliet.todo_list.databinding.TodoListBinding

class TodoAdapter( var todos:MutableList<Todo>) :RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.TodoViewHolder {
        val binding=TodoListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TodoViewHolder(binding)
    }

    fun addTodo(todo: Todo){
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }
    fun deleteDoneTodos(){
        todos.removeAll(){ todo ->
            todo.isChecked
        }
        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
       var curTodo=todos[position]
        holder.bindViews(curTodo)
    }



    override fun getItemCount(): Int {
        return todos.size
    }
    class TodoViewHolder(var binding: TodoListBinding):RecyclerView.ViewHolder(binding.root){
        fun bindViews(curTodo:Todo){
            binding.tvTodoList.text=curTodo.title
            binding.cbDone.isChecked=curTodo.isChecked
            toggleStrikeThrough(binding.tvTodoList,curTodo.isChecked)
            binding.cbDone.setOnCheckedChangeListener{_,isChecked ->
                toggleStrikeThrough(binding.tvTodoList,isChecked)
            }
        }
        private fun toggleStrikeThrough(tvTodoList: TextView,isChecked:Boolean){
            if (isChecked){
                tvTodoList.paintFlags=tvTodoList.paintFlags or STRIKE_THRU_TEXT_FLAG
            }else{
                tvTodoList.paintFlags=tvTodoList.paintFlags or STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
    }
}
