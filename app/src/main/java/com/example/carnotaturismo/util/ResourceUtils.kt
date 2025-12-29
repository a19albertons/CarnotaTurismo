package com.example.carnotaturismo.util

import android.content.Context
import com.example.carnotaturismo.R

/**
 * Utilidad para resolver recursos provenientes de la base de datos.
 * - Si la base de datos almacena una clave de recurso (p. ej. "lugar_1_titulo"), devuelve la cadena localizada.
 * - Si no se encuentra la clave de recurso, devuelve la cadena de respaldo `R.string.string_no_encontrada`.
 * - De lo contrario devuelve una cadena vacía para valores nulos o en blanco.
 */

fun Context.resolveString(value: String?): String {
    if (value.isNullOrBlank()) return ""
    // Intenta buscar un recurso de cadena con este nombre
    val resId = resources.getIdentifier(value, "string", packageName)
    return if (resId != 0) getString(resId) else getString(R.string.string_no_encontrada)
}
