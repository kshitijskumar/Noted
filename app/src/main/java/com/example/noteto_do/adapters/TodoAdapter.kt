package com.example.noteto_do.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteto_do.R
import com.example.noteto_do.database.TodoClass

class TodoAdapter( var todoItemsList: List<TodoClass>, private val clicks: CancelAndCompleteClicks) :
    RecyclerView.Adapter<TodoAdapter.ToDoViewholder>() {

    class ToDoViewholder(view: View): RecyclerView.ViewHolder(view){
        val titleView: TextView = view.findViewById(R.id.tv_title_todo)
        val timeView: TextView = view.findViewById(R.id.tv_time_todo)
        val descriptionView: TextView = view.findViewById(R.id.tv_description_todo)

        val cancelView: ImageView= view.findViewById(R.id.iv_cancel_todo)
        val completeView: ImageView= view.findViewById(R.id.iv_complete_todo)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewholder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.todo_list_element,parent,false)
        return ToDoViewholder(view)
    }

    override fun onBindViewHolder(holder: ToDoViewholder, position: Int) {
        holder.titleView.text= todoItemsList[position].task
        holder.timeView.text= todoItemsList[position].time
        holder.descriptionView.text= todoItemsList[position].toDoDescription

        holder.cancelView.setOnClickListener{
            clicks.cancelTodoClick(todoItemsList[position])
        }
        holder.completeView.setOnClickListener {
            clicks.completeTodoClick(todoItemsList[position])
        }
    }

    override fun getItemCount(): Int {
        return todoItemsList.size
    }

    interface CancelAndCompleteClicks{
        fun cancelTodoClick(task: TodoClass)
        fun completeTodoClick(task: TodoClass)
    }

}