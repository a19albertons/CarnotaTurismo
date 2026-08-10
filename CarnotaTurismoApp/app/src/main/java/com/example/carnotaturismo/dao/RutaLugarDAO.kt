package com.example.carnotaturismo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.carnotaturismo.model.RutasConLugares

@Dao
interface RutaLugarDAO {
    /**
     * Obtiene todas las rutas con sus lugares (relación N:N).
     */
    @Transaction
    @Query("SELECT * FROM rutas")
    fun obtenerTodas(): LiveData<List<RutasConLugares>>
    /**
     * Obtiene una ruta junto con sus lugares (relación N:N).
     */
    @Transaction
    @Query("SELECT * FROM rutas WHERE id = :id")
    fun obtenerRutaConLugares(id: Int): LiveData<RutasConLugares>
}