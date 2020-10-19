package com.example.noteto_do

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteto_do.adapters.TodoAdapter
import com.example.noteto_do.database.TodoClass
import com.example.noteto_do.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_todo.*

@AndroidEntryPoint
class ToDoFragment : Fragment(R.layout.fragment_todo), TodoAdapter.CancelAndCompleteClicks {

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_todo_list.layoutManager= LinearLayoutManager(activity)
        val adapter= TodoAdapter(listOf(),this)
        rv_todo_list.adapter= adapter
        viewModel.getAllTodoFromRepo().observe(viewLifecycleOwner,{
            pbTodoFragment.visibility= View.GONE
            adapter.todoItemsList= it
            adapter.notifyDataSetChanged()
        })

    }

    //Callback function from adapter which informs which task got canceled/deleted
    override fun cancelTodoClick(task: TodoClass) {
        viewModel.deleteTodoFromRepo(task)
        Toast.makeText(activity,"Task deleted",Toast.LENGTH_SHORT).show()
    }

    //Callback function from adapter which informs which task got completed
    override fun completeTodoClick(task: TodoClass) {
        viewModel.deleteTodoFromRepo(task)
        Toast.makeText(activity,"Wonderful!! Task completed",Toast.LENGTH_SHORT).show()
    }
}
