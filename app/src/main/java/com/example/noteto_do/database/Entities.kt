package com.example.noteto_do.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "todo_table")
class TodoClass(
    @ColumnInfo(name = "Task")
    var task: String,
    @ColumnInfo(name = "Description")
    var toDoDescription: String="None",
    @ColumnInfo(name = "Time")
    var time: String="-/-"
) {
    @ColumnInfo(name = "Id")
    @PrimaryKey(autoGenerate = true)
    var toDoId: Int=0
}


@Entity(tableName = "note_table")
class NoteClass(
    @ColumnInfo(name = "Title")
    var title: String = "Untitled",
    @ColumnInfo(name = "Description")
    var noteDescription: String= "Nothing noted."
){
    @ColumnInfo(name = "Id")
    @PrimaryKey(autoGenerate = true)
    var noteId: Int = 0
}