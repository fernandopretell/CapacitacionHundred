package com.example.capacitacionhundred.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.capacitacionhundred.database.Nota
import com.example.capacitacionhundred.repository.NotaRepository

class NotaViewModel(application: Application) : AndroidViewModel(application){

    private var notaRepository: NotaRepository? = null
    private var list_notas: LiveData<List<Nota>>? = null

    init {
        notaRepository = NotaRepository(application)
        list_notas = notaRepository?.listar_notas()
    }

    fun insert(nota:Nota){
        notaRepository?.insert(nota)
    }

    fun listarNotas():LiveData<List<Nota>>?{
        return list_notas
    }
}