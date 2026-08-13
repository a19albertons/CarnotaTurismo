// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.androidx.navigation.safeargs) apply false

    // Ksp en lugar de kapt
    alias(libs.plugins.ksp) apply false

    // Generación de documentación con Dokka
    alias(libs.plugins.kotlin.dokka)

    // Ktlint para formatear el código
    alias(libs.plugins.ktlint)
}
