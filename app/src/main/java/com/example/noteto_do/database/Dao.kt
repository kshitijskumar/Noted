package com.example.noteto_do.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TodoClass)

    @Delete
    suspend fun deleteTask(task: TodoClass)

    @Query("SELECT * FROM todo_table")
    fun getTasks(): LiveData<List<TodoClass>>

}

@Dao
interface NoteDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note:NoteClass)

    @Delete
    suspend fun deleteNote(note: NoteClass)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: NoteClass)

    @Query("SELECT * FROM note_table")
    fun getAllNotes(): LiveData<List<NoteClass>>
}