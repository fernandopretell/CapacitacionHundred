package com.example.capacitacionhundred.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotaDao {

    @Insert
    fun insert(nota: Nota)

    @Update
    fun update(nota: Nota)

    @Delete
    fun delete( nota: Nota)

    @Query("DELETE FROM TABLE_NOTAS")
    fun deleteAll()

    @Query("SELECT *FROM TABLE_NOTAS order by id desc")
    fun listarNotas():LiveData<List<Nota>>



}