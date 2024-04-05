package com.example.leozinhonotescrud.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.leozinhonotescrud.R
import com.example.leozinhonotescrud.database.NoteDAO
import com.example.leozinhonotescrud.databinding.ActivityAddNoteBinding
import com.example.leozinhonotescrud.model.Note

class AddNoteActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddNoteBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        var note: Note? = null
        val bundle = intent.extras
        if (bundle != null) {
            note = bundle.getSerializable("note") as Note
            binding.editNote.setText(note.description)
        }

        binding.btnSave.setOnClickListener {
            if (binding.editNote.text.toString().isNotEmpty()) {
                if (note != null) {
                    edit(note)
                }else{
                    save()
                }
            }else {
                Toast.makeText(this, "Error saving", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun save() {
        val description = binding.editNote.text.toString()
        val note = Note(0, description, "default")
        val noteDao = NoteDAO(this)
        if (noteDao.save(note)) {
            Log.i("Note", "Note saved successfully ${note.description}")
            Toast.makeText(this, "Note saved successfully", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
    fun edit(note: Note){
        val description = binding.editNote.text.toString()
        val note = Note(-1, description, "default")
        val noteDao = NoteDAO(this)
        if (noteDao.save(note)){
         Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}