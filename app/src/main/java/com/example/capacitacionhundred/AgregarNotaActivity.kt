package com.example.capacitacionhundred

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_agregar_nota.*

class AgregarNotaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_nota)

        btnGrabar.setOnClickListener {
            val titulo = etTitulo.text.toString()
            val descripcion = etDescripcion.text.toString()

            val i = Intent()
            i.putExtra("titulo",titulo)
            i.putExtra("descripcion",descripcion)

            setResult(Activity.RESULT_OK,i)
            finish()

            //grabo los datos y termina el activity volviendo al activity que lo llamo
        }
    }
}
