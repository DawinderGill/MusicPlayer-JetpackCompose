/**
 * This block defines the plugins used in the project.
 *
 * 1. `com.android.application`: The Android Application plugin. This plugin is used to build Android applications. It is applied to the top-level project and defines the android block where you configure the Android-specific aspects of the project.
 *
 * 2. `org.jetbrains.kotlin.android`: The Kotlin Android plugin. This plugin is used in conjunction with the Android plugin to add Kotlin support to Android projects.
 *
 * 3. `com.google.devtools.ksp`: Kotlin Symbol Processing (KSP) plugin. KSP provides a simplified compilation pipeline that accelerates the build process when using code generation libraries such as Dagger or Room.
 *
 * 4. `com.google.dagger.hilt.android`: The Dagger Hilt plugin. This plugin is used to facilitate dependency injection in Android applications. Hilt is built on top of Dagger to simplify the configuration and usage of Dagger in Android projects.
 *
 * 5. `org.jetbrains.kotlin.jvm`: The Kotlin JVM plugin. This plugin is used to compile Kotlin sources to Java bytecode, targeting the JVM.
 */
plugins {
    id(Plugins.pluginAndroidApplication) version Versions.pluginAndroidApplication apply false
    id(Plugins.pluginKotlinAndroid) version Versions.pluginKotlinAndroid apply false
    id(Plugins.pluginDevtoolsKSP) version Versions.pluginDevtoolsKSP apply false
    id(Plugins.pluginHiltAndroid) version Versions.pluginHiltAndroid apply false
    id(Plugins.pluginKotlinJVM) version Versions.pluginKotlinJVM apply false
}

