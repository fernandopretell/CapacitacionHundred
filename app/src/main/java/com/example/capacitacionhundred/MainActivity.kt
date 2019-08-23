package com.example.capacitacionhundred

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capacitacionhundred.database.Nota
import com.example.capacitacionhundred.database.NotaDatabase
import com.example.capacitacionhundred.viewmodel.NotaViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var database:NotaDatabase? = null
    var list_nota:List<Nota>? = ArrayList()

    private var notaViewModel : NotaViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv.layoutManager = LinearLayoutManager(this)
        rv.setHasFixedSize(true)

        val notaAdapter = NotaAdapter()
        rv.adapter = notaAdapter

        database = NotaDatabase.getInstance(this)
        //list_nota = database?.notaDao()?.listarNotas()

        notaViewModel = ViewModelProviders.of(this).get(NotaViewModel::class.java)

        notaViewModel?.listarNotas()?.observe(this, Observer {
            Toast.makeText(this,"Ok",Toast.LENGTH_SHORT).show()
        })
    }
}
