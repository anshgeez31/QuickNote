package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), INotesRVAdapter {

     private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notesRecyclerView = findViewById<RecyclerView>(R.id.rvNotes)
        val adapter = NotesRVAdapter(this, this)
        notesRecyclerView.layoutManager = LinearLayoutManager(this)
        notesRecyclerView.adapter = adapter


        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]

        viewModel.allNotes.observe(this) { list ->
            list?.let {
                adapter.updateList(it)
            }
        }

        val addNote = findViewById<Button>(R.id.btnSubmit)

        addNote.setOnClickListener{
            val input = findViewById<EditText>(R.id.etInput)
            val noteText = input.text.toString()
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this, "$noteText Inserted", Toast.LENGTH_LONG).show()
        }

    }

        override fun onItemClicked(note: Note) {
            viewModel.deleteNote(note)
            Toast.makeText(this, "${note.text} Deleted", Toast.LENGTH_LONG).show()
        }

    }