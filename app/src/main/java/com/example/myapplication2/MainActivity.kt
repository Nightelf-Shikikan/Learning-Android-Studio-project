package com.example.myapplication2

import android.widget.Toast

import android.widget.Button
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.ViewModelProvider

import android.widget.EditText

import com.example.myapplication2.utils.getCurrentDate

// ---------------- MainActivity -----------------
class MainActivity : AppCompatActivity() {
    private lateinit var adapter: NotesAdapter
    private lateinit var viewModel: NotesViewModel
    val notes = NotesRepository.notes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // RecyclerView setup
        adapter = NotesAdapter(emptyList())
        val recyclerView = findViewById<RecyclerView>(R.id.notesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // ViewModel
        viewModel = ViewModelProvider(this)[NotesViewModel::class.java]

        // Observe notes
        viewModel.notes.observe(this) { noteList ->
            adapter.updateNotes(noteList)
        }

        val titleInput = findViewById<EditText>(R.id.titleInput)
        val contentInput = findViewById<EditText>(R.id.contentInput)
        val addButton = findViewById<Button>(R.id.addNoteButton)

        addButton.setOnClickListener {
            val title = titleInput.text.toString()
            val content = contentInput.text.toString()

            if (title.isNotEmpty() && content.isNotEmpty()) {
                val newNote = Note(
                    id = NotesRepository.notes.size + 1,
                    title = title,
                    content = content,
                    date = getCurrentDate()
                )


                viewModel.addNote(newNote) // Add note via ViewModel

                titleInput.text.clear()
                contentInput.text.clear()
            } else {
                Toast.makeText(this, "Please enter title and content", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}
