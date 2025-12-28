package com.example.carnotaturismo.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.carnotaturismo.model.Lugar
import androidx.lifecycle.LiveData


@Dao
interface LugarDAO {
    @Query("select * from ubicaciones")
    fun obtenerTodos(): LiveData<List<Lugar>>
}