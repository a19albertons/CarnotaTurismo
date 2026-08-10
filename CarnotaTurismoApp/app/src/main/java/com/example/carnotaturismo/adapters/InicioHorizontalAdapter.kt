package com.example.carnotaturismo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carnotaturismo.R
import com.example.carnotaturismo.fragments.InicioFragmentDirections
import com.example.carnotaturismo.model.Lugar
import androidx.navigation.findNavController
import com.example.carnotaturismo.util.ImageHelper
import com.example.carnotaturismo.util.resolveString


/**
 * Adaptador para el RecyclerView horizontal.
 *
 */
class InicioHorizontalAdapter(
    private var lista: List<Lugar>,
    private val onToggleFavorito: (Lugar) -> Unit
) : RecyclerView.Adapter<InicioHorizontalAdapter.ViewHolder>() {

    // 1. ViewHolder: Guarda las referencias a las vistas de cada fila
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen: ImageView = itemView.findViewById<ImageView>(R.id.foto_inicio)
        val titulo: TextView = itemView.findViewById(R.id.titulo)
        val ubicacion: TextView = itemView.findViewById(R.id.ubicacion)
        val tarjeta: LinearLayout = itemView.findViewById(R.id.tarjeta)
        val favorito: ImageView = itemView.findViewById(R.id.iv_favorito)
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
        // Cargar imagen desde la referencia almacenada en la BD (p. ej. "@drawable/nombre")
        val ctx = holder.itemView.context
        ImageHelper.setImageFromRef(ctx, currentItem.imagen, holder.imagen)
        holder.ubicacion.text = ctx.resolveString(currentItem.ubicacion)
        holder.titulo.text = ctx.resolveString(currentItem.titulo)
        // Mostrar icono favorito segun BD
        holder.favorito.setImageResource(if (currentItem.favorito) R.drawable.favorito else R.drawable.no_favorito)

        // Toggle favorito en click
        holder.favorito.setOnClickListener {
            // Invierte el estado actual
            val nuevo = !currentItem.favorito
            currentItem.favorito = nuevo
            // Actualiza el icono
            holder.favorito.setImageResource(if (nuevo) R.drawable.favorito else R.drawable.no_favorito)
            // Notificar al fragmento para que actualice la BD
            onToggleFavorito(currentItem)
        }
        holder.tarjeta.setOnClickListener {
            holder.itemView.findNavController().navigate(InicioFragmentDirections.actionInicioFragmentToLugarDetallesFragment(currentItem))
        }

    }

    // 4. Devuelve el número total de elementos
    override fun getItemCount() = lista.size
}