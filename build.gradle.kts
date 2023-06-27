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
    id("com.android.application") version "8.1.0-beta03" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("org.jetbrains.kotlin.jvm") version "1.8.10" apply false
}
