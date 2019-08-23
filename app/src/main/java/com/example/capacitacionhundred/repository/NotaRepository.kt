package com.example.capacitacionhundred.repository

import android.annotation.SuppressLint
import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.room.Database
import com.example.capacitacionhundred.database.Nota
import com.example.capacitacionhundred.database.NotaDao
import com.example.capacitacionhundred.database.NotaDatabase

class NotaRepository(application: Application) {

    private var notaDAo: NotaDao? = null
    var list_notas: LiveData<List<Nota>>? = null

    init {
        val database = NotaDatabase.getInstance(application)
        notaDAo = database?.notaDao()
        list_notas = notaDAo?.listarNotas()
    }

    fun listar_notas(): LiveData<List<Nota>>?{
        return list_notas
    }

    fun insert(nota: Nota){
        InsertNotaAsyncTask(notaDAo).execute(nota)
    }

    fun delete(nota: Nota){
        DeleteNotaAsyncTask(notaDAo).execute(nota)
    }

    @SuppressLint("StaticFieldLeak")
    inner class DeleteNotaAsyncTask(private val notaDao: NotaDao?) : AsyncTask<Nota,Void,Void>(){

        override fun doInBackground(vararg notas: Nota): Void? {
            notaDao?.delete(notas[0])
            return  null
        }

    }

    @SuppressLint("StaticFieldLeak")
    inner class InsertNotaAsyncTask(private val notaDao: NotaDao?) : AsyncTask<Nota,Void,Void>(){

        override fun doInBackground(vararg notas: Nota): Void? {
            notaDao?.insert(notas[0])
            return  null
        }

    }
}