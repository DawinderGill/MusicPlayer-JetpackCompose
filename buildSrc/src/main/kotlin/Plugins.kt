/**
 * This object defines the plugins used in the project.
 *
 * - `pluginAndroidApplication`: The plugin for an Android application project.
 *
 * - `pluginKotlinAndroid`: The plugin for a Kotlin Android project.
 *
 * - `pluginDevtoolsKSP`: The plugin for Devtools Kotlin Symbol Processing (KSP).
 *
 * - `pluginHiltAndroid`: The plugin for Hilt Android dependency injection.
 *
 * - `pluginKotlinJVM`: The plugin for a Kotlin JVM project.
 *
 * - `pluginKapt`: The plugin for Kotlin annotation processing (KAPT).
 */
object Plugins {
    const val pluginAndroidApplication = "com.android.application"
    const val pluginKotlinAndroid = "org.jetbrains.kotlin.android"
    const val pluginDevtoolsKSP = "com.google.devtools.ksp"
    const val pluginHiltAndroid = "com.google.dagger.hilt.android"
    const val pluginKotlinJVM = "org.jetbrains.kotlin.jvm"
    const val pluginKapt = "kapt"
}