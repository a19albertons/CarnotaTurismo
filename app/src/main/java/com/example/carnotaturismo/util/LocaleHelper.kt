package com.example.carnotaturismo.util

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

 /**
 * Helper para gestionar locales de la aplicación.
 */
object LocaleHelper {
    // Convierte un nombre legible de idioma a un language tag
    fun tagForName(name: String): String? {
        return when (name) {
            "Sistema" -> null
            "Español" -> "es"
            "Galego" -> "gl"
            "English" -> "en"
            else -> name // assume it's already a language tag
        }
    }

    // Aplica el locale dado el nombre legible del idioma
    fun applyLocaleName(name: String) {
        val tag = tagForName(name)
        // If tag is null or empty, pass an empty language tags string which produces an empty LocaleListCompat
        val locales = LocaleListCompat.forLanguageTags(tag ?: "")
        AppCompatDelegate.setApplicationLocales(locales)
    }
}