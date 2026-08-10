package com.example.carnotaturismo.util

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import java.text.Normalizer

object LocaleHelper {
    // Convierte un nombre legible de idioma a un language tag
    fun tagForName(name: String): String? {
        val normalized = name.trim().lowercase()
        // Elimina la combinacion de diacríticos para que 'Español' y 'Espanol' coincidan igual
        val collapsed = Normalizer.normalize(normalized, Normalizer.Form.NFD).replace("\\p{M}+".toRegex(), "")
        return when {
            // defecto del sistema
            collapsed == "sistema" || collapsed == "system" -> null
            // español
            collapsed == "espanol" || collapsed == "spanish" -> "es"
            // gallego
            collapsed == "galego" || collapsed == "galician" -> "gl"
            // inglés
            collapsed == "english" || collapsed == "ingles" -> "en"
            // si parece un language tag, devolverlo tal cual
            Regex("^[a-z]{2,3}(-[A-Za-z]{2,})?$").matches(collapsed) -> name
            else -> null
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
