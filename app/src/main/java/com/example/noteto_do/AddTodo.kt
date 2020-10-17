package com.example.noteto_do

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.noteto_do.database.TodoClass
import com.example.noteto_do.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_add_todo.*

@AndroidEntryPoint
class AddTodo : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        btnAddTodoCancel.setOnClickListener{
            finish()
        }

        btnAddTodoDone.setOnClickListener{
            if(etAddTodoTitle.text.isEmpty()){
                Toast.makeText(this,"Task title field can't be empty",Toast.LENGTH_SHORT).show()
            }else{
                addNewTask()
            }
        }
    }

    fun addNewTask(){

        val taskTitle= etAddTodoTitle.text.toString()
        val taskTime= if(etAddTodoDate.text.isEmpty()) "-/-" else etAddTodoDate.text.toString()
        val taskDescription= if (etAddTodoDescription.text.isEmpty()) "No description provided" else etAddTodoDescription.text.toString()
        viewModel.insertTodoToRepo(TodoClass(taskTitle, taskDescription, taskTime))

        Toast.makeText(this,"Task added",Toast.LENGTH_SHORT).show()
        finish()
    }

}