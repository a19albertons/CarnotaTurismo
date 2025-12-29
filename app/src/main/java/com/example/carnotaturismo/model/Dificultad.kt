package com.example.carnotaturismo.model

import com.example.carnotaturismo.R

/**
 * Enum que representa la dificultad de una ruta.
 */
enum class Dificultad {
    BAJA,
    MEDIA,
    ALTA;

    /**
     * Devuelve el id de recurso de la etiqueta para esta dificultad.
     */
    fun labelRes(): Int = when (this) {
        BAJA -> R.string.dificultad_baja
        MEDIA -> R.string.dificultad_media
        ALTA -> R.string.dificultad_alta
    }
}