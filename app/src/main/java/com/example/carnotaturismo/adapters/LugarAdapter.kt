package com.example.carnotaturismo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.carnotaturismo.R
import com.example.carnotaturismo.fragments.InicioFragmentDirections
import com.example.carnotaturismo.fragments.LugarFragmentDirections
import com.example.carnotaturismo.fragments.VerTodosFragmentDirections
import com.example.carnotaturismo.model.Lugar

class LugarAdapter(private var lugares: List<Lugar>)
    : RecyclerView.Adapter<LugarAdapter.ViewHolder>() {

    // 1. ViewHolder: Guarda las referencias a las vistas de cada fila
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen: ImageView = itemView.findViewById(R.id.foto_inicio)
        val titulo: TextView = itemView.findViewById(R.id.titulo)
        val ubicacion: TextView = itemView.findViewById(R.id.ubicacion)
        val tarjeta: View = itemView.findViewById(R.id.tarjeta)
    }

    // Permite actualizar los datos y refrescar la vista
    fun setData(newModel: List<Lugar>) {
        lugares = newModel
        notifyDataSetChanged()
    }

    // 2. Crea nuevos ViewHolders (infla el layout de la fila)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_lugar, parent, false)
        return ViewHolder(view)
    }

    // 3. Reemplaza el contenido de una vista (une los datos a la vista)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = lugares[position]

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

        holder.titulo.text = currentItem.titulo
        holder.ubicacion.text = currentItem.ubicacion
        holder.tarjeta.setOnClickListener {
            holder.itemView.findNavController().navigate(LugarFragmentDirections.actionLugarFragmentToLugarDetallesFragment(currentItem))
        }
    }

    // 4. Devuelve el número total de elementos
    override fun getItemCount() = lugares.size

}