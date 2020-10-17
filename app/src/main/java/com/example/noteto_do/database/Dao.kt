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