package com.example.leozinhonotescrud.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.leozinhonotescrud.databinding.ItemNoteBinding
import com.example.leozinhonotescrud.model.Note

class NoteAdapter(
    val onclickDelete: (Int) -> Unit, val onclickEdit: (Note) -> Unit)
    : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var listNotes = emptyList<Note>()

    fun addList(list: List<Note>) {
        this.listNotes = list
        notifyDataSetChanged()
    }
    inner class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binding(note: Note) {
            binding.textDate.text = note.dateRegister
            binding.textDescription.text = note.description
            binding.btnEdit.setOnClickListener {
                onclickEdit(note)
            }
            binding.btnDelete.setOnClickListener {
                onclickDelete(note.idNote)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(view)
    }
    override fun getItemCount(): Int = listNotes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = listNotes[position]
        holder.binding(note)
    }
}