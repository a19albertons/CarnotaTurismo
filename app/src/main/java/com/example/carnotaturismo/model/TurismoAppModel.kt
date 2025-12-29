package com.example.carnotaturismo.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.carnotaturismo.dao.RutaLugarDAO
import com.example.carnotaturismo.db.TurismoDatabase

/**
 * Modelo de datos para el turismo app.
 *
 * @param application Aplicación.
 */
class TurismoAppModel(application: Application) : AndroidViewModel(application) {
    /**
     * Base de datos de turismo
     */
    private val database = TurismoDatabase.getDatabase(application)

    /**
     * DAO de lugares
     */
    private val lugarDao = database.lugarDao()

    /**
     * DAO de rutas
     */
    private val rutasDao = database.rutaDao()

    /**
     * DAO RutaLugar
     */
    private val rutaLugar = database.rutaLugarDao()



    /**
     * Lista obtenible de lugares
     */
    val lugares: LiveData<List<Lugar>> = lugarDao.obtenerTodos()

    /**
     * Lista obtenible de rutas
     */
    val rutas: LiveData<List<Rutas>> = rutasDao.obtenerTodas()

    /**
     * Obtiene una ruta junto con sus lugares (relación N:N) por id.
     */
    fun getRutaConLugares(id: Int): LiveData<RutasConLugares> = rutaLugar.obtenerRutaConLugares(id)
}