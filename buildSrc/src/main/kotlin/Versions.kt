/**
 * This object defines the versions for the dependencies used in the project.
 *
 * `coreKtx`: The version for the Core Kotlin Extensions library. This library provides Kotlin-friendly functionality for Android APIs.
 *
 * `composeBomCommon`: The version for the Compose Bill of Materials (BOM). The BOM is used to ensure that all Compose dependencies are using the same version.
 *
 * `lifecycleCommon`: The version for the Android Lifecycle library. This library helps manage lifecycle-aware components in Android.
 *
 * `activityCompose`: The version for the Activity Compose library, which brings Jetpack Compose to activities.
 *
 * `composeMaterial`: The version for the Compose Material library, which provides Material Design components for use with Jetpack Compose.
 *
 * `constraintLayoutCompose`: The version for the ConstraintLayout Compose library, which brings ConstraintLayout functionality to Jetpack Compose.
 *
 * `glideCompose`: The version for the Glide Compose library, which is used for image loading in Jetpack Compose.
 *
 * `lottieCompose`: The version for the Lottie Compose library, which is used to display Lottie animations in Jetpack Compose.
 *
 * `hiltAndroid`: The version for the Hilt Android library, which is used for dependency injection in Android.
 *
 * `hiltAndroidCompiler`: The version for the Hilt Android Compiler, which is used during compile time to generate dependency injection code.
 *
 * `mediaExoPlayer`: The version for the Media ExoPlayer library, which is used for playing audio and video in Android.
 *
 * `testJUnit`: The version for the JUnit testing library for unit tests.
 *
 * `androidTestJUnit`: The version for the Android JUnit testing library for instrumented tests.
 *
 * `androidTestEspresso`: The version for the Espresso testing library for UI tests.
 *
 * `androidTestJUnit4`: The version for the JUnit 4 testing library for Android tests.
 *
 * `pluginAndroidApplication`: The version for the Android Application plugin.
 *
 * `pluginKotlinAndroid`: The version for the Kotlin Android plugin.
 *
 *  `pluginKotlinJVM`: The version for the Kotlin JVM plugin.
 *
 *  `pluginDevtoolsKSP`: The version for the Devtools KSP plugin.
 *
 *  `pluginHiltAndroid`: The version for the Hilt Android plugin.
 */
object Versions {
    const val coreKtx = "1.10.1"

    const val composeBomCommon = "2023.03.00"
    const val lifecycleCommon = "2.6.1"
    const val activityCompose = "1.7.2"
    const val composeMaterial = "1.4.3"
    const val constraintLayoutCompose = "1.0.1"
    const val glideCompose = "1.0.0-alpha.1"
    const val lottieCompose = "6.0.1"
    //const val lottieCompose = "5.0.3"

    const val hiltAndroid = "2.46"
    const val hiltAndroidCompiler = "2.44"

    const val mediaExoPlayer = "1.0.2"

    const val testJUnit = "4.13.2"
    const val androidTestJUnit = "1.1.5"
    const val androidTestEspresso = "3.5.1"
    const val androidTestJUnit4 = "1.4.3"

    //Plugins
    const val pluginAndroidApplication = "8.2.0-alpha11"
    //const val pluginAndroidApplication = "8.1.0-rc01"
    const val pluginKotlinAndroid = "1.8.10"
    const val pluginKotlinJVM = "1.8.10"
    const val pluginDevtoolsKSP = "1.8.10-1.0.9"
    const val pluginHiltAndroid = "2.44"
}