package com.example.leozinhonotescrud.database

import com.example.leozinhonotescrud.model.Note

interface INoteDAO {
    fun save(note: Note): Boolean
    fun update(note: Note): Boolean
    fun delete(idNote: Int): Boolean
    fun getAll(): List<Note>
}