package com.example.carnotaturismo.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.carnotaturismo.model.Lugar
import androidx.lifecycle.LiveData

/**
 * Interfaz de acceso a datos para la entidad Lugar.
 */
@Dao
interface LugarDAO {
    /**
     * Obtiene todos los lugares.
     *
     * @return Lista de lugares.
     */
    @Query("select * from ubicaciones")
    fun obtenerTodos(): LiveData<List<Lugar>>
}