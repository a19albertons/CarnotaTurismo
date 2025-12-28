package com.example.carnotaturismo.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class RutasConLugares(
    @Embedded val ruta: Rutas,

    @Relation(
        parentColumn = "id",     // ID en RutaEntity
        entityColumn = "id",     // ID en LugarEntity
        associateBy = Junction(
            value = RutaLugar::class,
            parentColumn = "rutaId",
            entityColumn = "lugarId"
        )
    )
    val lugares: List<Lugar>
) {

}