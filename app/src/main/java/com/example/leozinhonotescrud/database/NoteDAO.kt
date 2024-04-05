package com.example.leozinhonotescrud.database

import android.content.ContentValues
import android.content.Context
import com.example.leozinhonotescrud.model.Note
import com.example.leozinhonotescrud.util.Const

class NoteDAO(context: Context) : INoteDAO {

    val read = DataBaseHelper(context).readableDatabase
    val write = DataBaseHelper(context).writableDatabase

    override fun save(note: Note): Boolean {
        val content = ContentValues()
        content.put(Const.COLUM_DESCRIPTION, note.description)
        try {
            write.insert(Const.NAME_TABLE, null, content)
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }

    override fun update(note: Note): Boolean {
        try {
            val content = ContentValues()
            content.put(Const.COLUM_DESCRIPTION, note.description)
            val args = arrayOf(note.idNote.toString())
            write.update(Const.NAME_TABLE, content, "${Const.COLUM_ID_NOTE} = ?", args)
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }

    override fun delete(idNote: Int): Boolean {

        try {
            val args = arrayOf(idNote.toString())
            write.delete(Const.NAME_TABLE, "${Const.COLUM_ID_NOTE} = ?", args)
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }

    override fun getAll(): List<Note> {
        val listNotes = mutableListOf<Note>()
        val sql = "SELECT ${Const.COLUM_ID_NOTE}, " +
                "${Const.COLUM_DESCRIPTION}, " +
                "    strftime('%d/%m/%Y %H:%M', ${Const.COLUM_DATE_REGISTER}) ${Const.COLUM_DATE_REGISTER} " +
                "FROM ${Const.NAME_TABLE}"

        val cursor = read.rawQuery(sql, null)
        val indexId = cursor.getColumnIndex(Const.COLUM_ID_NOTE)
        val indexDescription = cursor.getColumnIndex(Const.COLUM_DESCRIPTION)
        val indexDateRegister = cursor.getColumnIndex(Const.COLUM_DATE_REGISTER)

        while (cursor.moveToNext()) {
            val idNote = cursor.getInt(indexId)
            val description = cursor.getString(indexDescription)
            val dateRegister = cursor.getString(indexDateRegister)
            listNotes.add(Note(idNote, description, dateRegister))
        }
        return listNotes
    }


}