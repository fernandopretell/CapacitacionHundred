package com.example.capacitacionhundred.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "table_notas")
data class Nota(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id:Int,

    @ColumnInfo(name = "titulo")
    var titutlo:String,

    @ColumnInfo(name = "descripcion")
    var descripcion:String
) {
    @Ignore
    constructor(titulo:String, descripcion: String):this(0,titulo,descripcion)
}