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

    /**
     * Obtiene las rutas favoritas.
     *
     * @return Lista de rutas favoritas.
     */
    @Query("select * from rutas where favorito = 1")
    fun obtenerRutasFavoritas(): LiveData<List<Rutas>>

    /**
     * Marca o desmarca una ruta como favorita (0/1)
     */
    @Query("UPDATE rutas SET favorito = :valor WHERE id = :id")
    suspend fun setFavoritoRuta(
        id: Int,
        valor: Int,
    )
}
