package com.dawinder.musicplayer_jetpackcompose.di

import com.dawinder.musicplayer_jetpackcompose.repositories.TrackRepository
import com.dawinder.musicplayer_jetpackcompose.repositories.TrackRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideTrackRepository(trackRepository: TrackRepositoryImpl): TrackRepository {
        return trackRepository
    }
}