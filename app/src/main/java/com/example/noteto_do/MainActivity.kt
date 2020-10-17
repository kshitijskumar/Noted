package com.example.noteto_do

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, ToDoFragment(),"TodoFragment")
            commit()
        }

        topNavigationView.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.itemNote -> supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment,NoteFragment(),"NoteFragment")
                    commit()
                }
                R.id.itemToDo -> supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment,ToDoFragment(),"TodoFragment")
                    commit()
                }
            }
            true

        }

        fab_add.setOnClickListener {

            val currentFragment= supportFragmentManager.findFragmentById(R.id.flFragment)
            if(currentFragment!=null && currentFragment.isVisible){
                when(currentFragment.tag){
                    "TodoFragment" -> {
                        val intent= Intent(this,AddTodo::class.java)
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
}