package com.example.carnotaturismo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.carnotaturismo.R
import com.example.carnotaturismo.fragments.ItinerarioListaFragmentDirections
import com.example.carnotaturismo.model.Rutas
import com.example.carnotaturismo.util.ImageHelper
import com.example.carnotaturismo.util.resolveString

/**
 * Adaptado para el recyclerView de itinerario lista
 */
class ItinerarioListaAdapter(
    private var itineraios: List<Rutas>,
    private val onToggleFavorito: (Rutas) -> Unit,
) : RecyclerView.Adapter<ItinerarioListaAdapter.ViewHolder>() {
    // 1. ViewHolder: Guarda las referencias a las vistas de cada fila
    class ViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {
        val imagen: ImageView = itemView.findViewById(R.id.foto_inicio)
        val titulo: TextView = itemView.findViewById(R.id.titulo)
        val duracion: TextView = itemView.findViewById(R.id.duracion)
        val km: TextView = itemView.findViewById(R.id.km)
        val dificultad: TextView = itemView.findViewById(R.id.dificultad)
        val tarjeta: View = itemView.findViewById(R.id.tarjeta)
        val favorito: ImageView = itemView.findViewById(R.id.iv_favorito)
    }

    // Permite actualizar los datos y refrescar la vista
    fun setData(newModel: List<Rutas>) {
        itineraios = newModel
        notifyDataSetChanged()
    }

    // 2. Crea nuevos ViewHolders (infla el layout de la fila)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.adapter_itinerario_lista, parent, false)
        return ViewHolder(view)
    }

    // 3. Reemplaza el contenido de una vista (une los datos a la vista)
    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        val currentItem = itineraios[position]

        // Cargar imagen desde la referencia almacenada en la BD (p. ej. "@drawable/nombre")
        val ctx = holder.itemView.context
        ImageHelper.setImageFromRef(ctx, currentItem.imagen, holder.imagen)

        holder.titulo.text = ctx.resolveString(currentItem.titulo)
        holder.duracion.text = ctx.resolveString(currentItem.duracion)
        holder.km.text = currentItem.km.toString()
        // Mostrar la etiqueta localizable asociada al enum Dificultad
        holder.dificultad.text = ctx.getString(currentItem.dificultad.labelRes())
        // Mostrar icono favorito según BD
        holder.favorito.setImageResource(if (currentItem.favorito) R.drawable.favorito else R.drawable.no_favorito)

        // Toggle favorito en click
        holder.favorito.setOnClickListener {
            // Invierte el estado actual
            val nuevo = !currentItem.favorito
            currentItem.favorito = nuevo
            // Actualiza el icono
            holder.favorito.setImageResource(if (nuevo) R.drawable.favorito else R.drawable.no_favorito)
            // Notifica al fragmento para que actualice la BD
            onToggleFavorito(currentItem)
        }

        holder.tarjeta.setOnClickListener {
            holder.itemView.findNavController().navigate(
                ItinerarioListaFragmentDirections.actionItinerarioListaFragmentToItinerarioDetallesFragment(currentItem),
            )
        }
    }

    // 4. Devuelve el número total de elementos
    override fun getItemCount() = itineraios.size
}
