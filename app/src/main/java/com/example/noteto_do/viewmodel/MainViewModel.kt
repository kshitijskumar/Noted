package com.example.noteto_do.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteto_do.database.TodoClass
import com.example.noteto_do.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : ViewModel(){

    fun getAllTodoFromRepo()= repository.getAllTodo()

    fun deleteTodoFromRepo(task: TodoClass){
        viewModelScope.launch {
            repository.deleteTodo(task)
        }
    }

    fun insertTodoToRepo(task: TodoClass){
        viewModelScope.launch {
            repository.insertTodo(task)
        }
    }


}