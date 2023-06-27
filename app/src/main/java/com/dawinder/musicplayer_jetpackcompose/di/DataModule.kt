package com.dawinder.musicplayer_jetpackcompose.di

import com.dawinder.musicplayer_jetpackcompose.repositories.TrackRepository
import com.dawinder.musicplayer_jetpackcompose.repositories.TrackRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * A Dagger Hilt module that provides an instance of [TrackRepository].
 * This module is installed in the [SingletonComponent], meaning that the provided [TrackRepository]
 * instance will be a singleton.
 */
@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    /**
     * Provides a singleton instance of [TrackRepository].
     *
     * @param trackRepository An instance of [TrackRepositoryImpl] which is a concrete implementation of [TrackRepository].
     * @return An instance of [TrackRepository].
     */
    @Provides
    @Singleton
    fun provideTrackRepository(trackRepository: TrackRepositoryImpl): TrackRepository {
        return trackRepository
    }
}