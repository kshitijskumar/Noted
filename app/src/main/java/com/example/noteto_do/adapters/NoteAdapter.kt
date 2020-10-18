package com.example.noteto_do.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteto_do.R
import com.example.noteto_do.database.NoteClass

class NoteAdapter(var notesList: List<NoteClass>, private val listener: OnCLickNotes) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val noteTitle: TextView= view.findViewById(R.id.tvNoteTitle)
        val noteDescription: TextView= view.findViewById(R.id.tvNoteDescription)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.note_elements, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote= notesList[position]
        holder.noteTitle.text= currentNote.title
        holder.noteDescription.text= currentNote.noteDescription

        holder.itemView.setOnClickListener {
            listener.noteClicked(currentNote)
        }

    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    interface OnCLickNotes{
        fun noteClicked(note: NoteClass)
    }
}