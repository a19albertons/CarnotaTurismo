package com.example.carnotaturismo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.carnotaturismo.model.Rutas

/**
 * Interfaz de acceso a datos para la entidad Rutas.
 */
@Dao
interface RutasDAO {
    /**
     * Obtiene todas las rutas.
     *
     * @return Lista de rutas.
     */
    @Query("select * from rutas")
    fun obtenerTodas(): LiveData<List<Rutas>>
}