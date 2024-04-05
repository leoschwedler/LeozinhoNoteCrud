package com.example.leozinhonotescrud.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.leozinhonotescrud.util.Const

class DataBaseHelper(context: Context) :
    SQLiteOpenHelper(context, Const.NAME_DB, null, Const.VERSION) {


    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE ${Const.NAME_TABLE}(" +
                "${Const.COLUM_ID_NOTE} INTEGER not NULL PRIMARY KEY AUTOINCREMENT," +
                "${Const.COLUM_DESCRIPTION} VARCHAR (70)," +
                "${Const.COLUM_DATE_REGISTER} DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP)"

        try {
            db?.execSQL(sql)
            Log.i("INFO DB", "Sucesso ao criar a tabela")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("INFO DB", "Erro ao criar a tabela")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}