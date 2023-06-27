package com.dawinder.musicplayer_jetpackcompose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Custom [Application] class for the project.
 *
 * This class uses Dagger's Hilt library for dependency injection. The [HiltAndroidApp] annotation triggers
 * Hilt's code generation, including a base class for the application that serves as the application-level
 * dependency container.
 *
 * An application container is the parent container in a Hilt application, and other containers in the app
 * can access the dependencies that it provides.
 */
@HiltAndroidApp
class MyApplication : Application()