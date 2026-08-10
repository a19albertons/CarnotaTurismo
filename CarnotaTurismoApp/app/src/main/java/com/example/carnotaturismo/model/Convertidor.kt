package com.example.carnotaturismo.model

import androidx.room.TypeConverter

/**
 * Convertidor para tipos usados en Room (TipoUbicacion y Dificultad).
 */
class Convertidor {
    // TipoUbicacion
    @TypeConverter
    fun fromTipoUbicacion(value: TipoUbicacion): String {
        return value.name
    }

    @TypeConverter
    fun toTipoUbicacion(value: String): TipoUbicacion {
        return runCatching { TipoUbicacion.valueOf(value) }.getOrDefault(TipoUbicacion.Monumento)
    }

    // Dificultad
    @TypeConverter
    fun fromDificultad(value: Dificultad): String {
        return value.name
    }

    @TypeConverter
    fun toDificultad(value: String): Dificultad {
        return runCatching { Dificultad.valueOf(value) }.getOrDefault(Dificultad.MEDIA)
    }
}