package com.example.carnotaturismo.model

import androidx.lifecycle.LiveData
import com.example.carnotaturismo.db.TurismoDatabase

/**
 * Repository que encapsula el acceso a datos (Room DAOs) para la aplicación.
 */
class TurismoRepository(database: TurismoDatabase) {
    private val lugarDao = database.lugarDao()
    private val rutasDao = database.rutaDao()
    private val rutaLugarDao = database.rutaLugarDao()

    // Metodos para obtener todo
    fun obtenerTodosLugares(): LiveData<List<Lugar>> = lugarDao.obtenerTodos()
    fun obtenerTodasRutas(): LiveData<List<Rutas>> = rutasDao.obtenerTodas()

    // Metodos para obtener favoritos
    fun obtenerLugaresFavoritos(): LiveData<List<Lugar>> = lugarDao.obtenerLugaresFavoritos()
    fun obtenerRutasFavoritas(): LiveData<List<Rutas>> = rutasDao.obtenerRutasFavoritas()

    // Metodos para obtener Rutas con Lugares
    fun obtenerRutasConLugares(): LiveData<List<RutasConLugares>> = rutaLugarDao.obtenerTodas()
    fun obtenerRutaConLugares(id: Int): LiveData<RutasConLugares> = rutaLugarDao.obtenerRutaConLugares(id)

    // Métodos para modificar favorito (suspend)
    // Para cambiar el estado de favoritos
    suspend fun setFavoritoLugar(id: Int, valor: Int) = lugarDao.setFavoritoLugar(id, valor)
    suspend fun setFavoritoRuta(id: Int, valor: Int) = rutasDao.setFavoritoRuta(id, valor)
}
