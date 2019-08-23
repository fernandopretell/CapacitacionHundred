package com.example.capacitacionhundred

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capacitacionhundred.database.Nota
import com.example.capacitacionhundred.database.NotaDatabase
import com.example.capacitacionhundred.viewmodel.NotaViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var database: NotaDatabase? = null
    var list_nota: List<Nota>? = ArrayList()

    private var notaViewModel: NotaViewModel? = null

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
            notaAdapter.setNotas(it)
        })

        //agregar_nota
        btn_agregar.setOnClickListener {
            val intent = Intent(this, AgregarNotaActivity::class.java)
            startActivityForResult(intent, 1000)
        }

        //Eliminar una nota
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //Eliminar
                notaViewModel?.delete(notaAdapter.obtenerNota(viewHolder.adapterPosition)) //obtener la nota pasando la posicion del item para eso creo un metodo en el adaptador
                Toast.makeText(this@MainActivity, "Nota eliminada", Toast.LENGTH_SHORT).show()
            }

        }).attachToRecyclerView(rv)

        notaAdapter.setOnItemClickListener(object : NotaAdapter.OnItemClickListener {
            override fun onItemClick(nota: Nota) {

            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1000 && resultCode == Activity.RESULT_OK) {
            val titulo: String? = data?.getStringExtra("titulo")
            val descripcion: String? = data?.getStringExtra("descripcion")

            val nota = Nota(titulo!!, descripcion!!)
            notaViewModel?.insert(nota)

            Toast.makeText(this@MainActivity, "Nota insertada", Toast.LENGTH_SHORT).show()


        }


    }
}
