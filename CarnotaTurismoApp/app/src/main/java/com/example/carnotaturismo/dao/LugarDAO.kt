package com.example.carnotaturismo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.carnotaturismo.model.Lugar

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

    /**
     * Obtiene los lugares favoritos.
     *
     * @return Lista de lugares favoritos.
     */
    @Query("select * from ubicaciones where favorito = 1")
    fun obtenerLugaresFavoritos(): LiveData<List<Lugar>>

    /**
     * Marca o desmarca un lugar como favorito (0/1)
     */
    @Query("UPDATE ubicaciones SET favorito = :valor WHERE id = :id")
    suspend fun setFavoritoLugar(
        id: Int,
        valor: Int,
    )
}
