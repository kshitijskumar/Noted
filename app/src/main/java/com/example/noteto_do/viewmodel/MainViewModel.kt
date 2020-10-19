package com.example.noteto_do.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteto_do.database.NoteClass
import com.example.noteto_do.database.TodoClass
import com.example.noteto_do.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : ViewModel(){

    val currentFragmentTag= MutableLiveData("TodoFragment")


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

    fun insertNoteToRepo(note: NoteClass){
        viewModelScope.launch {
            repository.insertNote(note)
        }
    }

    fun getAllNotesFromRepo()= repository.getAllNotes()

    fun updateNoteToRepo(note: NoteClass){
        viewModelScope.launch {
            repository.updateNote(note)
        }
    }

    fun deleteNoteToRepo(note: NoteClass){
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }

}