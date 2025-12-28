package com.example.carnotaturismo.model

import androidx.room.TypeConverter

class Convertidor {
    @TypeConverter
    fun fromTipoUbicacion(value: TipoUbicacion): String {
        return value.name
    }

    @TypeConverter
    fun toTipoUbicacion(value: String): TipoUbicacion {
        return runCatching { TipoUbicacion.valueOf(value) }.getOrDefault(TipoUbicacion.Monumento)
    }
}