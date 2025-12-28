package com.example.carnotaturismo.model

import androidx.room.Entity

@Entity(primaryKeys = ["rutaId", "lugarId"])
data class RutaLugar(
    val rutaId: Int,
    val lugarId: Int

) {

}