package com.example.carnotaturismo.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Clase que representa un lugar y todos los datos que construyen la aplicacion
 */
@Parcelize
@Entity(tableName = "ubicaciones")
data class Lugar(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val titulo: String,
    val ubicacion: String,
    val leyenda: String,
    val descripcion: String,
    val imagen: String,
    val imagenMapa: String,
    val imagenMapaEnlace: String,
    val tipo: TipoUbicacion,
    val importante: String,
    var favorito: Boolean,
) : Parcelable
