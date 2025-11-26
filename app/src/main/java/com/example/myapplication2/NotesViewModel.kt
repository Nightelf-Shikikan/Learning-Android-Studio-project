package com.example.myapplication2

// NotesViewModel.kt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotesViewModel : ViewModel() {

    private val _notes = MutableLiveData<List<Note>>(NotesRepository.notes)
    val notes: LiveData<List<Note>> = _notes

    fun addNote(note: Note) {
        NotesRepository.notes.add(note)
        _notes.value = NotesRepository.notes.toList() // trigger LiveData observers
    }
}