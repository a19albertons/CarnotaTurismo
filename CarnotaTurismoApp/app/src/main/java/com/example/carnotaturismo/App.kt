package com.example.carnotaturismo

import android.app.Application
import com.example.carnotaturismo.util.LocaleHelper

 /**
 * Clase Application para inicializar configuraciones globales.
 */
class App : Application() {
    // Al iniciar la aplicación, aplicar el idioma guardado en preferencias
    override fun onCreate() {
        super.onCreate()
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        val lang = prefs.getString("pref_idioma", "Sistema") ?: "Sistema"
        LocaleHelper.applyLocaleName(lang)
    }
}