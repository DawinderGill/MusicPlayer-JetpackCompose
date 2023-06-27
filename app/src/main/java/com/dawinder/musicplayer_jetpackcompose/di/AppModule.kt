package com.dawinder.musicplayer_jetpackcompose.di

import android.app.Application
import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import com.dawinder.musicplayer_jetpackcompose.player.MyPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * A Hilt module that provides dependencies for the application.
 * Modules are classes that use the @Module annotation and are responsible for providing objects that can be injected.
 * The @InstallIn annotation tells Hilt which Android class to attach the module to.
 * SingletonComponent::class means the module will be installed in the application's singleton component.
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    /**
     * Provides the [Context] for the application.
     * The @Provides annotation tells Hilt that this function provides a dependency.
     * @Singleton means that a single instance of the provided object will be used in the whole app.
     *
     * @param application The [Application] instance of the app.
     * @return The application [Context].
     */
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    /**
     * Provides an instance of [ExoPlayer].
     * ExoPlayer is a media player for Android. It's being built with the provided application context.
     *
     * @param context The application [Context].
     * @return An instance of [ExoPlayer].
     */
    @Provides
    @Singleton
    fun provideExoPLayer(context: Context): ExoPlayer {
        return ExoPlayer.Builder(context).build()
    }

    /**
     * Provides an instance of [MyPlayer].
     * MyPlayer is a custom wrapper class around ExoPlayer.
     *
     * @param player An instance of [ExoPlayer].
     * @return An instance of [MyPlayer].
     */
    @Provides
    @Singleton
    fun provideMyPlayer(player: ExoPlayer): MyPlayer {
        return MyPlayer(player)
    }
}