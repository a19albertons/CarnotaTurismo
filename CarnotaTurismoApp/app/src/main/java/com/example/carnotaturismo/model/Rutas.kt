package com.example.carnotaturismo.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Clase que representa una ruta y todos los datos que construyen la aplicacion
 */
@Parcelize
@Entity(tableName = "rutas")
data class Rutas(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val titulo: String,
    val duracion: String,
    val km: Double,
    val dificultad: Dificultad,
    val imagen: String,
    val imagenMapa: String,
    val imagenMapaEnlace: String,
    val leyenda: String,
    // val lugares: List<Lugar>,
    val descripcion: String,
    val importante: String,
    var favorito: Boolean,
) : Parcelable
