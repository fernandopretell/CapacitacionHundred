package com.example.capacitacionhundred

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.capacitacionhundred.database.Nota
import org.w3c.dom.Text

class NotaAdapter : RecyclerView.Adapter<NotaAdapter.NotaAdapterViewHolder>(){

    private var list_notas: List<Nota>? = ArrayList<Nota>()
    var listener:OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notas,parent,false)
        return  NotaAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list_notas?.size!!
    }

    override fun onBindViewHolder(holder: NotaAdapterViewHolder, position: Int) {
        val nota = list_notas?.get(position)

        holder.tv_titulo?.text = nota?.titutlo
        holder.tv_descripcion?.text = nota?.descripcion

        holder.contenedor?.setOnClickListener {
            var position = holder.adapterPosition
            if (listener != null && position != RecyclerView.NO_POSITION){
                listener?.onItemClick(list_notas?.get(position)!!)
            }
        }
    }

    fun setNotas(list_notas: List<Nota>){
        this.list_notas = list_notas
        notifyDataSetChanged()
    }

    fun obtenerNota(position: Int):Nota{
        return list_notas?.get(position)!!
    }

    fun setOnItemClickListener(listener:OnItemClickListener){
        this.listener = listener

    }

    class NotaAdapterViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!){
        var tv_titulo: TextView? = null
        var tv_descripcion: TextView? = null
        var contenedor: CardView? = null

        init {
            tv_titulo = itemView?.findViewById(R.id.tvTitulo)
            tv_descripcion = itemView?.findViewById(R.id.tvdescripcion)
            contenedor = itemView?.findViewById(R.id.contenedor)
        }
    }

    interface OnItemClickListener{
        fun onItemClick(nota:Nota)
    }
}