package com.example.carnotaturismo.model

import androidx.room.Entity

/**
 * Clase que representa la relación N:N entre ruta y lugar
 */
@Entity(primaryKeys = ["rutaId", "lugarId"])
data class RutaLugar(
    val rutaId: Int,
    val lugarId: Int

) {

}