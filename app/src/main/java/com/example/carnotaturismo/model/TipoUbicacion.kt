package com.example.carnotaturismo.model

import com.example.carnotaturismo.R

/**
 * Clase que representa el tipo de ubicación
 */
enum class TipoUbicacion {
    Monumento,
    Senderismo,
    Playa;

    /**
     * Devuelve el id de recurso de la etiqueta para este tipo de ubicación.
     */
    fun labelRes(): Int = when (this) {
        Monumento -> R.string.tipo_monumento
        Senderismo -> R.string.tipo_senderismo
        Playa -> R.string.tipo_playa
    }
}