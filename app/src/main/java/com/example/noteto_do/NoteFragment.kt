package com.example.noteto_do

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.noteto_do.adapters.NoteAdapter
import com.example.noteto_do.database.NoteClass
import com.example.noteto_do.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_note.*

@AndroidEntryPoint
class NoteFragment(private val listener: NoteItemClicked) : Fragment(R.layout.fragment_note), NoteAdapter.OnCLickNotes{

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvNote.layoutManager= StaggeredGridLayoutManager(2,RecyclerView.VERTICAL)
        val adapter= NoteAdapter(listOf(),this)
        rvNote.adapter= adapter

        viewModel.getAllNotesFromRepo().observe(viewLifecycleOwner,{
            pbNoteFragment.visibility= View.GONE
            adapter.notesList= it
            adapter.notifyDataSetChanged()
        })
    }

    override fun noteClicked(note: NoteClass) {
        listener.noteItemClicked(note)
    }

    interface NoteItemClicked{
        fun noteItemClicked(note: NoteClass)
    }
}
