import java.util.*
import com.dawinder.buildsrc.*

/**
 * Local properties loaded from the root project file.
 */
val localProperties = Properties().apply {
    load(rootProject.file(Constants.LOCAL_PROPERTIES).inputStream())
}

/**
 * Base URL for the API used by the app, loaded from local properties.
 */
val baseUrl: String = localProperties.getProperty(Constants.BASE_URL) ?: Constants.EMPTY_STRING

// Define the plugins for the application
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

// Define the Android configurations for the application.
android {
    /**
     * The namespace for the resources in the project.
     */
    namespace = AppConfigs.namespace
    /**
     * The Android SDK version that the project should be compiled against.
     */
    compileSdk = AppConfigs.compileSdk

    /**
     * Enable or disable specific features for the build.
     */
    buildFeatures {
        buildConfig = true
    }

    /**
     * Configurations that apply to all build variants.
     */
    defaultConfig {
        applicationId = AppConfigs.applicationId
        minSdk = AppConfigs.minSdk
        targetSdk = AppConfigs.targetSdk
        versionCode = AppConfigs.versionCode
        versionName = AppConfigs.versionName

        testInstrumentationRunner = AppConfigs.testInstrumentationRunner

        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField(Constants.BASE_URL_TYPE, Constants.BASE_URL, "\"$baseUrl\"")
    }

    /**
     * Configuration for the different types of builds (e.g., debug, release).
     */
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(Constants.PROGUARD_ANDROID_OPTIMIZE),
                Constants.PROGUARD_RULES
            )
        }
    }
    /**
     * Configurations for the Java compiler.
     */
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    /**
     * Configurations for the Kotlin compiler.
     */
    kotlinOptions {
        jvmTarget = AppConfigs.jvmTarget
    }
    /**
     * Enable or disable Jetpack Compose.
     */
    buildFeatures {
        compose = true
    }
    /**
     * Configurations for Jetpack Compose.
     */
    composeOptions {
        kotlinCompilerExtensionVersion = AppConfigs.kotlinCompilerExtensionVersion
    }
    /**
     * Configurations for Jetpack Compose.
     */
    packaging {
        resources {
            excludes += Constants.EXCLUDES
        }
    }
}

// Configure Kotlin Annotation Processing (Kapt).
kapt { correctErrorTypes = true }

// Define the dependencies for the application.
dependencies {
    // Core Libraries
    implementation(Deps.coreKtx)

    // Jetpack Compose and related libraries
    implementation(platform(Deps.composeBom))
    androidTestImplementation(Deps.composeBom)
    implementation(Deps.composeUI)
    implementation(Deps.composeUiGraphics)
    implementation(Deps.composeUiToolingPreview)
    implementation(Deps.composeMaterial3)
    implementation(Deps.composeMaterial)
    implementation(Deps.activityCompose)
    implementation(Deps.constraintLayoutCompose)
    implementation(Deps.lifecycRuntimeKtx)
    implementation(Deps.lifecycleViewModelCompose)
    implementation(Deps.lifecycleViewModelKtx)
    implementation(Deps.lifecycleRuntimeCompose)

    // Dagger Hilt for Dependency Injection
    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltAndroidCompiler)

    // Media and Image Handling
    implementation(Deps.mediaExoPlayer)
    implementation(Deps.glideCompose)
    implementation(Deps.lottieCompose)

    // Testing Libraries
    testImplementation(Deps.testJUint)
    androidTestImplementation(Deps.androidTestJUnit)
    androidTestImplementation(Deps.androidTestExpresso)
    androidTestImplementation(Deps.testComposeUiTestJUnit4)

    // Debugging Tools
    debugImplementation(Deps.debugComposeUiTooling)
    debugImplementation(Deps.debugComposeUiTestManifest)
}