import java.util.*
import com.dawinder.buildsrc.*

val localProperties = Properties().apply {
    load(rootProject.file(Constants.LOCAL_PROPERTIES).inputStream())
}
val baseUrl: String = localProperties.getProperty(Constants.BASE_URL) ?: Constants.EMPTY_STRING

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = AppConfigs.namespace
    compileSdk = AppConfigs.compileSdk

    buildFeatures {
        buildConfig = true
    }

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

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(Constants.PROGUARD_ANDROID_OPTIMIZE),
                Constants.PROGUARD_RULES
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = AppConfigs.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AppConfigs.kotlinCompilerExtensionVersion
    }
    packaging {
        resources {
            excludes += Constants.EXCLUDES
        }
    }
}

kapt { correctErrorTypes = true }

dependencies {
    //Compose Platform
    implementation(platform(Deps.composeBom))
    androidTestImplementation(Deps.composeBom)

    //Core KTX
    implementation(Deps.coreKtx)

    //Compose Libraries
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

    //Dagger Hilt
    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltAndroidCompiler)

    //Media Exoplayer
    implementation(Deps.mediaExoPlayer)

    //Glide
    implementation(Deps.glideCompose)

    //Lottie
    implementation(Deps.lottieCompose)

    //Testing
    testImplementation(Deps.testJUint)
    androidTestImplementation(Deps.androidTestJUnit)
    androidTestImplementation(Deps.androidTestExpresso)
    androidTestImplementation(Deps.testComposeUiTestJUnit4)

    //Debug
    debugImplementation(Deps.debugComposeUiTooling)
    debugImplementation(Deps.debugComposeUiTestManifest)
}