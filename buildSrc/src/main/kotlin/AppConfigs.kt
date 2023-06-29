/**
 * This object defines the application configurations for the project.
 *
 * `namespace`: The namespace for the project resources. This is usually the package name of the application.
 *
 * `applicationId`: The unique identifier for the application, typically expressed as a package name.
 *
 * `testInstrumentationRunner`: The test runner class for Android instrumentation tests.
 *
 * `compileSdk`: The API level of the Android SDK that the build tools compile the project's source code against.
 *
 * `minSdk`: The minimum API level required to run the application.
 *
 * `targetSdk`: The API level of the Android platform that the app is designed to run on.
 *
 * `versionCode`: An integer value representing the version of the application code, relative to other versions.
 *
 * `versionName`: A string value representing the release version of the application as it should be shown to users.
 *
 * `jvmTarget`: The target version of the JVM that the Kotlin compiler should generate JVM bytecode for.
 *
 * `kotlinCompilerExtensionVersion`: The version of Kotlin Compiler Extensions used in the project.
 */
object AppConfigs {
    const val namespace = "com.dawinder.musicplayer_jetpackcompose"
    const val applicationId = "com.dawinder.musicplayer_jetpackcompose"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    const val compileSdk = 33
    const val minSdk = 21
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "1.0"

    const val jvmTarget = "17"

    const val kotlinCompilerExtensionVersion = "1.4.3"
}