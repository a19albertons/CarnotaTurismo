package com.example.carnotaturismo.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.carnotaturismo.db.TurismoDatabase
import com.example.carnotaturismo.model.Lugar
import com.example.carnotaturismo.model.Rutas
import com.example.carnotaturismo.model.RutasConLugares
import com.example.carnotaturismo.repo.TurismoRepository
import com.example.carnotaturismo.util.LocaleHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Modelo de datos para el turismo app.
 *
 * @param application Aplicación.
 */
class TurismoAppModel(
    application: Application,
) : AndroidViewModel(application) {
    /**
     * Base de datos de turismo
     */
    private val database = TurismoDatabase.Companion.getDatabase(application)

    /**
     * Repository que encapsula acceso a datos
     */
    private val repository = TurismoRepository(database)

    /**
     * Lista obtenible de lugares
     */
    val lugares: LiveData<List<Lugar>> = repository.obtenerTodosLugares()

    /**
     * Lista obtenible de rutas
     */
    val rutas: LiveData<List<Rutas>> = repository.obtenerTodasRutas()

    /**
     * Lista obtenible de rutas con lugares
     */
    val rutasConLugares: LiveData<List<RutasConLugares>> = repository.obtenerRutasConLugares()

    /**
     * Obtiene una ruta junto con sus lugares (relación N:N) por id.
     */
    fun getRutaConLugares(id: Int): LiveData<RutasConLugares> = repository.obtenerRutaConLugares(id)

    /**
     * Obtiene los lugares favoritos.
     */
    fun obtenerLugaresFavoritos(): LiveData<List<Lugar>> = repository.obtenerLugaresFavoritos()

    /**
     * Obtiene las rutas favoritas.
     */
    fun obtenerRutasFavoritas(): LiveData<List<Rutas>> = repository.obtenerRutasFavoritas()

    /**
     * método para marcar/desmarcar favorito desde ViewModel
     */
    fun setFavoritoLugar(
        id: Int,
        valor: Int,
    ) {
        // No bloqueamos el hilo principal
        viewModelScope.launch(Dispatchers.IO) {
            repository.setFavoritoLugar(id, valor)
        }
    }

    fun setFavoritoRuta(
        id: Int,
        valor: Int,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setFavoritoRuta(id, valor)
        }
    }

    // Preferencias basicas: idioma y musica
    private val prefs = application.getSharedPreferences("settings", Context.MODE_PRIVATE)

    val idioma =
        MutableLiveData<String>().apply {
            value = prefs.getString("pref_idioma", "Sistema") ?: "Sistema"
        }

    val musica =
        MutableLiveData<Boolean>().apply {
            value = prefs.getBoolean("pref_musica", true)
        }

    fun setIdioma(nuevo: String) {
        idioma.value = nuevo
        // Aplicar locale inmediatamente en el hilo de UI
        viewModelScope.launch(Dispatchers.Main) {
            LocaleHelper.applyLocaleName(nuevo)
        }
        viewModelScope.launch(Dispatchers.IO) {
            prefs.edit().putString("pref_idioma", nuevo).apply()
        }
    }

    fun setMusica(activo: Boolean) {
        musica.value = activo
        viewModelScope.launch(Dispatchers.IO) {
            prefs.edit().putBoolean("pref_musica", activo).apply()
        }
    }
}
