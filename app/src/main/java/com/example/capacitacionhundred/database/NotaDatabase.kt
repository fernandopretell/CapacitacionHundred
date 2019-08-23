package com.example.capacitacionhundred.database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = arrayOf(Nota::class), version = 1)
abstract class NotaDatabase : RoomDatabase(){

    abstract fun notaDao():NotaDao

    companion object{

        private var instance: NotaDatabase? = null

        fun getInstance(context: Context):NotaDatabase?{

            if(instance == null){
                instance = Room.databaseBuilder<NotaDatabase>(context.applicationContext,
                    NotaDatabase::class.java,"db_notas")
                    .allowMainThreadQueries() //para llamar a base de datos en el hilo principal
                    .addCallback(room)
                    .build()
            }

            return instance
        }

        private val room = object  : RoomDatabase.Callback(){

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                PopulateDBAsyncTask(instance).execute()
            }
        }
    }



    class PopulateDBAsyncTask(instance: NotaDatabase?) : AsyncTask<Void,Void,Void>(){

        private val notaDao : NotaDao? = instance?.notaDao()

        override fun doInBackground(vararg p0: Void?): Void? {

            notaDao?.insert(Nota(titulo = "Titulo01",descripcion = "Descripcion01"))
            notaDao?.insert(Nota(titulo = "Titulo01",descripcion = "Descripcion01"))
            notaDao?.insert(Nota(titulo = "Titulo01",descripcion = "Descripcion01"))
            notaDao?.insert(Nota(titulo = "Titulo01",descripcion = "Descripcion01"))
            notaDao?.insert(Nota(titulo = "Titulo01",descripcion = "Descripcion01"))
            notaDao?.insert(Nota(titulo = "Titulo01",descripcion = "Descripcion01"))

            return null
        }

    }
}