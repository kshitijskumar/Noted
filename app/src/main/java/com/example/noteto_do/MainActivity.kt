package com.example.noteto_do

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.noteto_do.database.NoteClass
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , NoteFragment.NoteItemClicked {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayTodoFragment()

        //Navigation bar to toggle between todo and note
        topNavigationView.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.itemNote -> {
                    displayNoteFragment()
                }
                R.id.itemToDo -> {
                    displayTodoFragment()
                }
            }
            true

        }

        fab_add.setOnClickListener {

            val currentFragment= supportFragmentManager.findFragmentById(R.id.flFragment)
            if(currentFragment!=null && currentFragment.isVisible){
                when(currentFragment.tag){
                    "TodoFragment" -> {
                        val intent = Intent(this, AddTodo::class.java)
                        startActivity(intent)
                    }

                    "NoteFragment" -> {
                        val intent= Intent(this, AddNote::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }

    //Callback function from note fragment to change activity
    override fun noteItemClicked(note: NoteClass) {
        val noteTitle= note.title
        val noteDescription= note.noteDescription
        val noteId= note.noteId
        val intent= Intent(this, EditDeleteNote::class.java).apply {
            putExtra("Id",noteId)
            putExtra("Title",noteTitle)
            putExtra("Description", noteDescription)
        }
        startActivity(intent)
    }

    private fun displayTodoFragment(){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,ToDoFragment(),"TodoFragment")
            commit()
        }
    }

    private fun displayNoteFragment(){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,NoteFragment(this@MainActivity),"NoteFragment")
            commit()
        }
    }
}