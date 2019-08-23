package com.example.capacitacionhundred

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.capacitacionhundred.database.Nota
import com.example.capacitacionhundred.database.NotaDatabase

class MainActivity : AppCompatActivity() {

    var database:NotaDatabase? = null
    var list_nota:List<Nota>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = NotaDatabase.getInstance(this)
        list_nota = database?.notaDao()?.listarNotas()
    }
}
