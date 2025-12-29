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
import com.example.carnotaturismo.util.ImageHelper
import com.example.carnotaturismo.util.resolveString

/**
 * Adaptador para el RecyclerView de lugares (ubicaciones).
 */
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

        // Cargar imagen desde la referencia almacenada en la BD (p. ej. "@drawable/nombre")
        val ctx = holder.itemView.context
        ImageHelper.setImageFromRef(ctx, currentItem.imagen, holder.imagen)

        holder.titulo.text = ctx.resolveString(currentItem.titulo)
        holder.ubicacion.text = ctx.resolveString(currentItem.ubicacion)
        holder.tarjeta.setOnClickListener {
            holder.itemView.findNavController().navigate(LugarFragmentDirections.actionLugarFragmentToLugarDetallesFragment(currentItem))
        }
    }

    // 4. Devuelve el número total de elementos
    override fun getItemCount() = lugares.size

}