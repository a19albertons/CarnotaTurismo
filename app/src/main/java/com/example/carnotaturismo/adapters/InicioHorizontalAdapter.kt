package com.example.carnotaturismo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carnotaturismo.R
import com.example.carnotaturismo.model.Lugar

/**
 * Adaptador para el RecyclerView horizontal.
 *
 */
class InicioHorizontalAdapter(private var lista: List<Lugar>)
    : RecyclerView.Adapter<InicioHorizontalAdapter.ViewHolder>() {

    // 1. ViewHolder: Guarda las referencias a las vistas de cada fila
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.titulo)
    }

    // 2. Crea nuevos ViewHolders (infla el layout de la fila)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_inicio_horizontal, parent, false)
        return ViewHolder(view)
    }

    // 3. Reemplaza el contenido de una vista (une los datos a la vista)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = lista.get(position)
        holder.titulo.text = currentItem.titulo

    }

    // 4. Devuelve el número total de elementos
    override fun getItemCount() = lista.size
}