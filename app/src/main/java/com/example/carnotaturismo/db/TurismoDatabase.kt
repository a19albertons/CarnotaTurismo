package com.example.carnotaturismo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.carnotaturismo.dao.LugarDAO
import com.example.carnotaturismo.dao.RutasDAO
import com.example.carnotaturismo.model.Lugar
import com.example.carnotaturismo.model.Rutas

@Database(entities = [Lugar::class, Rutas::class], version = 1, exportSchema = false)
abstract class TurismoDatabase : RoomDatabase() {
    abstract fun lugarDao(): LugarDAO
    abstract fun rutaDao(): RutasDAO

    companion object {
        @Volatile
        private var INSTANCE: TurismoDatabase? = null

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
