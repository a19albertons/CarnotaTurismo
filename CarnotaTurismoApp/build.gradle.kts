// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("androidx.navigation.safeargs") version "2.9.6" apply false

    // Generación de documentación con Dokka
    alias(libs.plugins.kotlin.dokka)

    // Ktlint para formatear el código
    alias(libs.plugins.ktlint)
}