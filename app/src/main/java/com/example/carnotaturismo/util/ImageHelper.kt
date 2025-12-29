package com.example.carnotaturismo.util

import android.content.Context
import android.widget.ImageView
import com.example.carnotaturismo.R

/**
* Helper para manejo de imágenes.
*/
object ImageHelper {
    /**
     * Intenta cargar una imagen a partir de una referencia almacenada en la BD.
     * - Si imageRef tiene formato "@drawable/nombre" usa resources.getIdentifier
     * - Si no, intenta buscar directamente como drawable
     * - Si no encuentra nada, usa R.drawable.pruebas como fallback
     */
    fun setImageFromRef(ctx: Context, imageRef: String?, imageView: ImageView) {
        if (!imageRef.isNullOrBlank()) {
            val ref = imageRef.removePrefix("@")
            val parts = ref.split('/', limit = 2)
            if (parts.size == 2) {
                val type = parts[0]
                val name = parts[1]
                val resId = ctx.resources.getIdentifier(name, type, ctx.packageName)
                if (resId != 0) {
                    imageView.setImageResource(resId)
                    return
                }
            }
            // Intentar como drawable directamente (por si la BD guarda sólo el nombre)
            val direct = ctx.resources.getIdentifier(ref, "drawable", ctx.packageName)
            if (direct != 0) {
                imageView.setImageResource(direct)
                return
            }
        }
        // Imagen por defecto (error 404) si no se encuentra nada
        imageView.setImageResource(R.drawable.error_imagen)
    }
}