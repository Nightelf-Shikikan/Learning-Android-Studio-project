package com.example.myapplication2

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DiffUtil
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView

// NotesAdapter.kt
class NotesAdapter(private var notes: List<Note>) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.noteTitle)
        val content: TextView = itemView.findViewById(R.id.noteContent)
        val date: TextView = itemView.findViewById(R.id.noteDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.title.text = note.title
        holder.content.text = note.content
        holder.date.text = note.date
    }

    override fun getItemCount(): Int = notes.size

    // Update the list when LiveData changes
    fun updateNotes(newNotes: List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }
}