package com.example.leozinhonotescrud.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leozinhonotescrud.adapter.NoteAdapter
import com.example.leozinhonotescrud.database.NoteDAO
import com.example.leozinhonotescrud.databinding.ActivityMainBinding
import com.example.leozinhonotescrud.model.Note

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var listNotes = emptyList<Note>()
    private var noteAdapter: NoteAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
        noteAdapter = NoteAdapter({ id -> confirmDelete(id) }, { note -> editNote(note) })
        binding.rvMain.adapter = noteAdapter
        binding.rvMain.layoutManager = LinearLayoutManager(this)
    }

    private fun editNote(note: Note) {
        val intent = Intent(this, AddNoteActivity::class.java)
        intent.putExtra("note", note)
        startActivity(intent)

    }

    private fun confirmDelete(id: Int) {
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle("Delete Note")
        alertBuilder.setMessage("Do you want to delete the note?")
        alertBuilder.setPositiveButton("Yes") { _, _ ->
            val noteDAO = NoteDAO(this).delete(id)
            updateNotes()
        }
        alertBuilder.setNegativeButton("No") { _, _ -> }
        alertBuilder.create().show()
    }


    override fun onStart() {
        super.onStart()
        updateNotes()

    }

    private fun updateNotes() {
        listNotes = NoteDAO(this).getAll()
        noteAdapter?.addList(listNotes)
        Log.i("NOTES_DB", listNotes.toString())

    }
}