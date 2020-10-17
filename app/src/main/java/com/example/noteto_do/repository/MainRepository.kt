package com.example.noteto_do.repository

import com.example.noteto_do.database.ToDoDao
import com.example.noteto_do.database.TodoClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(
   private val toDoDao: ToDoDao
) {
    fun getAllTodo()= toDoDao.getTasks()

    suspend fun deleteTodo(task: TodoClass){
        withContext(Dispatchers.IO){
            toDoDao.deleteTask(task)
        }
    }

    suspend fun insertTodo(task: TodoClass){
        withContext(Dispatchers.IO){
            toDoDao.insertTask(task)
        }
    }
}