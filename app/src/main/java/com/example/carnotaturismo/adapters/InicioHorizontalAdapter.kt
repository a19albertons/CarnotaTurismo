package com.example.carnotaturismo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
        val imagen: ImageView = itemView.findViewById<ImageView>(R.id.foto_inicio)
        val titulo: TextView = itemView.findViewById(R.id.titulo)
        val ubicacion: TextView = itemView.findViewById(R.id.ubicacion)
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
        //val ctx = holder.itemView.context
        //val imgRef = currentItem.imagen

        // extrae "drawable" y "mi_imagen"
        //val (type, name) = imgRef.removePrefix("@").split('/', limit = 2)
        //val resId = ctx.resources.getIdentifier(name, type, ctx.packageName)
        //if (resId != 0) {
        //    holder.imagen.setImageResource(resId)
        //} else {
        //    holder.imagen.setImageResource(R.drawable.error_imagen) // fallback
        //}
        holder.ubicacion.text = currentItem.ubicacion
        holder.titulo.text = currentItem.titulo

    }

    // 4. Devuelve el número total de elementos
    override fun getItemCount() = lista.size
}