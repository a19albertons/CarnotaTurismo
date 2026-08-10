package com.example.carnotaturismo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.carnotaturismo.dao.LugarDAO
import com.example.carnotaturismo.dao.RutaLugarDAO
import com.example.carnotaturismo.dao.RutasDAO
import com.example.carnotaturismo.model.Lugar
import com.example.carnotaturismo.model.Rutas
import com.example.carnotaturismo.model.RutaLugar

/**
 * Base de datos de turismo.
 */
@Database(entities = [Lugar::class, Rutas::class, RutaLugar::class], version = 1, exportSchema = false)
@TypeConverters(com.example.carnotaturismo.model.Convertidor::class)
abstract class TurismoDatabase : RoomDatabase() {
    /**
     * Obtiene el DAO para la entidad Lugar.
     *
     * @return DAO para la entidad Lugar.
     */
    abstract fun lugarDao(): LugarDAO

    /**
     * Obtiene el DAO para la entidad Rutas.
     *
     * @return DAO para la entidad Rutas.
     */
    abstract fun rutaDao(): RutasDAO

    /**
     * Obtiene el DAO para la entidad RutaLugar.
     *
     * @return DAO para la entidad RutaLugar.
     */
    abstract fun rutaLugarDao(): RutaLugarDAO


    /**
     * Obtiene una instancia de la base de datos de turismo.
     *
     * @param context Contexto de la aplicación.
     * @return Instancia de la base de datos de turismo.
     */
    companion object {
        /**
         * Instancia de la base de datos de turismo.
         */
        @Volatile
        private var INSTANCE: TurismoDatabase? = null

        /**
         * Obtiene una instancia de la base de datos de turismo.
         *
         * @param context Contexto de la aplicación.
         */
        fun getDatabase(context: Context): TurismoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder<TurismoDatabase>(
                    context.applicationContext,
                    TurismoDatabase::class.java,
                    "turismo.db"
                ).createFromAsset("turismo.db").build()
                INSTANCE = instance
                instance
            }
        }
    }
}
