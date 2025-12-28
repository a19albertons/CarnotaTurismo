package com.example.carnotaturismo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carnotaturismo.R
import com.example.carnotaturismo.model.Lugar
import com.example.carnotaturismo.model.TipoUbicacion

/**
 * Adaptador para el RecyclerView vertical.
 *
 */
class InicioVerticalAdapter(private var tipos: Array<TipoUbicacion>, private var model: List<Lugar>)
    : RecyclerView.Adapter<InicioVerticalAdapter.ViewHolder>() {


    // 1. ViewHolder: Guarda las referencias a las vistas de cada fila
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tipoUbicacion: TextView = itemView.findViewById(R.id.tipo)
        val RecyclerViewHorizontal: RecyclerView = itemView.findViewById(R.id.RecyclerViewHorizontal)
    }

    // Permite actualizar los datos y refrescar la vista
    fun setData(newModel: List<Lugar>) {
        model = newModel
        notifyDataSetChanged()
    }

    // 2. Crea nuevos ViewHolders (infla el layout de la fila)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_inicio_vertical, parent, false)
        return ViewHolder(view)
    }

    // 3. Reemplaza el contenido de una vista (une los datos a la vista)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = tipos[position]
        holder.tipoUbicacion.text = currentItem.name

        // Asegurar que el RecyclerView horizontal tenga un LayoutManager para poder hacer el layout
        if (holder.RecyclerViewHorizontal.layoutManager == null) {
            holder.RecyclerViewHorizontal.layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
            holder.RecyclerViewHorizontal.setHasFixedSize(true)
        }

        // Asignar el adaptador (filtrado por tipo)
        holder.RecyclerViewHorizontal.adapter = InicioHorizontalAdapter(model.filter { it.tipo == currentItem })
    }

    // 4. Devuelve el número total de elementos
    override fun getItemCount() = tipos.size
}