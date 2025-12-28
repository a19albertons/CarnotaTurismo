package com.example.carnotaturismo.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.carnotaturismo.model.Rutas

@Dao
interface RutasDAO {
    @Query("select * from rutas")
    fun obtenerTodas(): LiveData<List<Rutas>>
}