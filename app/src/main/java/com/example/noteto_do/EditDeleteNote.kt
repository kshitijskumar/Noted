package com.example.noteto_do

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.noteto_do.database.NoteClass
import com.example.noteto_do.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_edit_delete_note.*

@AndroidEntryPoint
class EditDeleteNote : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val noteId by lazy { intent.getIntExtra("Id",0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_delete_note)

        val noteTitle= intent.getStringExtra("Title")
        val noteDescription= intent.getStringExtra("Description")

        etEditDelTitle.setText(noteTitle)
        etEditDelDescription.setText(noteDescription)

        btnEditDelNoteCancel.setOnClickListener {
            cancelDialogueCreate()
        }

        btnEditDelNoteDone.setOnClickListener {
            updateNote(noteId)
            finish()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_delete_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuDel -> {
                AlertDialog.Builder(this)
                    .setTitle("Delete Note")
                    .setMessage("Do you want to delete this note?")
                    .setPositiveButton("Yes"){ _, _ ->
                        deleteNote(noteId)
                        finish()
                    }
                    .setNegativeButton("No"){ _, _ ->
                        finish()
                    }
                    .create()
                    .show()
            }
        }
        return true
    }

    override fun onBackPressed() {
        cancelDialogueCreate()
    }

    private fun updateNote(noteId: Int){
        val note= NoteClass(etEditDelTitle.text.toString(),etEditDelDescription.text.toString())
        note.noteId= noteId

        viewModel.updateNoteToRepo(note)
    }

    private fun deleteNote(noteId: Int){
        val note= NoteClass(etEditDelTitle.text.toString(),etEditDelDescription.text.toString())
        note.noteId= noteId

        viewModel.deleteNoteToRepo(note)
        Toast.makeText(this,"Note Deleted",Toast.LENGTH_SHORT).show()
    }

    private fun cancelDialogueCreate(){
        val cancelDialogue= AlertDialog.Builder(this)
            .setTitle("Note")
            .setMessage("Do you want to cancel? All the changes will be lost")
            .setPositiveButton("Yes"){ _, _ ->
                finish()
            }
            .setNegativeButton("No"){ dialogInterface: DialogInterface, _ ->

                dialogInterface.dismiss()
            }
            .create()

        cancelDialogue.show()

    }
}