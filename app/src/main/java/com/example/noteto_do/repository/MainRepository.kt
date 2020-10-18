package com.example.noteto_do.repository

import com.example.noteto_do.database.NoteClass
import com.example.noteto_do.database.NoteDao
import com.example.noteto_do.database.ToDoDao
import com.example.noteto_do.database.TodoClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val toDoDao: ToDoDao,
    private val noteDao: NoteDao
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

    suspend fun insertNote(note: NoteClass){
        withContext(Dispatchers.IO){
            noteDao.insertNote(note)
        }
    }

    fun getAllNotes()= noteDao.getAllNotes()

    suspend fun updateNote(note: NoteClass){
        withContext(Dispatchers.IO){
            noteDao.updateNote(note)
        }
    }

    suspend fun deleteNote(note: NoteClass){
        withContext(Dispatchers.IO){
            noteDao.deleteNote(note)
        }
    }
}