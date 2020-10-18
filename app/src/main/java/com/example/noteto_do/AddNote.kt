package com.example.noteto_do

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.noteto_do.database.NoteClass
import com.example.noteto_do.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_add_note.*

@AndroidEntryPoint
class AddNote : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        btnAddNoteCancel.setOnClickListener {
            finish()
        }

        btnAddNoteDone.setOnClickListener {
            addNewNote()
        }
    }

    private fun addNewNote(){
        val noteTitle= if(etAddNoteTitle.text.isEmpty()) "Untitled" else etAddNoteTitle.text.toString()
        val noteDescription= if(etAddNoteDescription.text.isEmpty()) "Nothing noted" else etAddNoteDescription.text.toString()

        viewModel.insertNoteToRepo(NoteClass(noteTitle,noteDescription))
        Toast.makeText(this,"Note added", Toast.LENGTH_SHORT).show()
        finish()
    }
}