package com.example.carnotaturismo.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

/**
 * Clase que representa la relación N:N entre ruta y lugar
 */
@Entity(primaryKeys = ["rutaId", "lugarId"],
    foreignKeys = [
        ForeignKey(
            entity = Rutas::class,
            parentColumns = ["id"],
            childColumns = ["rutaId"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = Lugar::class,
            parentColumns = ["id"],
            childColumns = ["lugarId"],
            onDelete = CASCADE
        )
    ])

data class RutaLugar(
    val rutaId: Int,
    val lugarId: Int

) {

}